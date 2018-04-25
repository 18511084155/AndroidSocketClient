package com.woodys.android.oksocket;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.woodys.android.oksocket.data.MessageBean;
import com.woodys.android.oksocket.util.JsonUtils;
import com.woodys.android.oksocket.utils.AESEncoder;
import com.woodys.devicelib.KeplerSdk;
import com.woodys.devicelib.callback.DeviceInfoListener;
import com.woodys.libsocket.sdk.ConnectionInfo;
import com.woodys.libsocket.sdk.OkSocket;
import com.woodys.libsocket.sdk.OkSocketOptions;
import com.woodys.libsocket.sdk.SocketActionAdapter;
import com.woodys.libsocket.sdk.bean.IPulseSendable;
import com.woodys.libsocket.sdk.bean.ISendable;
import com.woodys.libsocket.sdk.bean.OriginalData;
import com.woodys.libsocket.sdk.connection.IConnectionManager;
import com.woodys.android.oksocket.adapter.LogAdapter;
import com.woodys.android.oksocket.data.HandShake;
import com.woodys.android.oksocket.data.LogBean;
import com.woodys.android.oksocket.data.MsgDataBean;
import com.woodys.libsocket.sdk.protocol.IHeaderProtocol;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import sun.misc.BASE64Encoder;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by woodys 2018/3/24.
 */

public class MainSimpleActivity extends AppCompatActivity {
    private ConnectionInfo mInfo;

    private Button mConnect;
    private IConnectionManager mManager;
    private EditText mSendET;
    private OkSocketOptions mOkOptions;
    private Button mClearLog;
    private Button mSendBtn;

    private RecyclerView mSendList;
    private RecyclerView mReceList;

    private LogAdapter mSendLogAdapter = new LogAdapter();
    private LogAdapter mReceLogAdapter = new LogAdapter();

    private SocketActionAdapter adapter = new SocketActionAdapter() {

        @Override
        public void onSocketConnectionSuccess(Context context, ConnectionInfo info, String action) {
            //mManager.send(new HandShake());
            mConnect.setText("DisConnect");
        }

        @Override
        public void onSocketDisconnection(Context context, ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                logSend("异常断开:" + e.getMessage());
            } else {
                Toast.makeText(context, "正常断开", LENGTH_SHORT).show();
                logSend("正常断开");
            }
            mConnect.setText("Connect");
        }

        @Override
        public void onSocketConnectionFailed(Context context, ConnectionInfo info, String action, Exception e) {
            Toast.makeText(context, "连接失败" + e.getMessage(), LENGTH_SHORT).show();
            logSend("连接失败");
            mConnect.setText("Connect");
        }

        @Override
        public void onSocketReadResponse(Context context, ConnectionInfo info, String action, OriginalData data) {
            super.onSocketReadResponse(context, info, action, data);
            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
            logRece(str);
        }

        @Override
        public void onSocketWriteResponse(Context context, ConnectionInfo info, String action, ISendable data) {
            super.onSocketWriteResponse(context, info, action, data);
            String str = new String(data.parse(), Charset.forName("utf-8"));
            logSend(str);
        }

        @Override
        public void onPulseSend(Context context, ConnectionInfo info, IPulseSendable data) {
            super.onPulseSend(context, info, data);
            String str = new String(data.parse(), Charset.forName("utf-8"));
            logSend(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_main);
        findViews();
        initData();
        setListener();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void findViews() {
        mSendList = (RecyclerView)findViewById(R.id.send_list);
        mReceList = (RecyclerView)findViewById(R.id.rece_list);
        mClearLog = (Button)findViewById(R.id.clear_log);
        mConnect = (Button)findViewById(R.id.connect);
        mSendET = (EditText)findViewById(R.id.send_et);
        mSendBtn = (Button)findViewById(R.id.send_btn);
    }

    private void initData() {
        LinearLayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSendList.setLayoutManager(manager1);
        mSendList.setAdapter(mSendLogAdapter);

        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mReceList.setLayoutManager(manager2);
        mReceList.setAdapter(mReceLogAdapter);

        mInfo = new ConnectionInfo("192.168.28.109", 59227);
        mOkOptions = new OkSocketOptions.Builder(OkSocketOptions.getDefault())
                .setReconnectionManager(new NoneReconnect())
                .build();

        //设置自定义解析头
        OkSocketOptions.Builder okOptionsBuilder = new OkSocketOptions.Builder(mOkOptions);
        okOptionsBuilder.setHeaderProtocol(new IHeaderProtocol() {
            @Override
            public int getHeaderLength() {
                //返回自定义的包头长度,框架会解析该长度的包头
                return 0;
            }

            @Override
            public int getBodyLength(byte[] header, ByteOrder byteOrder) {
                //从header(包头数据)中解析出包体的长度,byteOrder是你在参配中配置的字节序,可以使用ByteBuffer比较方便解析
                return 0;
            }
        });
        //将新的修改后的参配设置给连接管理器
        mManager = OkSocket.open(mInfo, okOptionsBuilder.build());
    }

    private void setListener() {
        mManager.registerReceiver(adapter);
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManager == null) {
                    return;
                }
                if (!mManager.isConnect()) {
                    mManager.connect();
                } else {
                    mConnect.setText("DisConnecting");
                    mManager.disconnect();
                }
            }
        });
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManager == null) {
                    return;
                }
                if (!mManager.isConnect()) {
                    Toast.makeText(getApplicationContext(), "未连接,请先连接", LENGTH_SHORT).show();
                } else {
                    String msg = mSendET.getText().toString();
                    if (TextUtils.isEmpty(msg.trim())) {
                        return;
                    }
                    sendMessage(getMessage(msg));
                    /*MsgDataBean msgDataBean = new MsgDataBean(msg);
                    mManager.send(msgDataBean);*/
                    mSendET.setText("");
                }
            }
        });
        mClearLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReceLogAdapter.getDataList().clear();
                mSendLogAdapter.getDataList().clear();
                mReceLogAdapter.notifyDataSetChanged();
                mSendLogAdapter.notifyDataSetChanged();
            }
        });
    }

    private String getMessage(String msg){
        MessageBean messageBean=new MessageBean();
        /** 下面四个字段必须传递 **/
        messageBean.userId="18511084155";
        messageBean.event="DATA";
        messageBean.userSource="FINGERPRINT";
        messageBean.appId="0008";
        /** 下面两个字段可传递 **/
        JSONObject deviceInfo = KeplerSdk.getInstance().getDeviceInfo(this);
        try {
            deviceInfo.put("appChannel","ceshi");
            deviceInfo.put("registerFrom","217");
            deviceInfo.put("uuid",msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        messageBean.data= deviceInfo.toString();
        messageBean.registerFrom="217";
        return JsonUtils.toJson(messageBean);
    }

    //发送消息
    private void sendMessage(final String content){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    byte[] data = AESEncoder.encrypt(content);
                    ByteArrayOutputStream arr = new ByteArrayOutputStream();
                    OutputStream zipper = new GZIPOutputStream(arr);
                    zipper.write(data);
                    zipper.flush();
                    zipper.close();
                    String encoder = Base64.encodeToString(arr.toByteArray(), Base64.NO_WRAP);
                    subscriber.onNext(encoder+"\r\n");
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new Action1<String>() {
                              @Override
                              public void call(String msg) {
                                  MsgDataBean msgDataBean = new MsgDataBean(msg);
                                  mManager.send(msgDataBean);
                              }
                          }, new Action1<Throwable>() {
                              @Override
                              public void call(Throwable e) {
                                  e.printStackTrace();
                              }
                          }
                );
    }

    private void logSend(String log) {
        LogBean logBean = new LogBean(System.currentTimeMillis(), log);
        mSendLogAdapter.getDataList().add(0, logBean);
        mSendLogAdapter.notifyDataSetChanged();
    }

    private void logRece(String log) {
        LogBean logBean = new LogBean(System.currentTimeMillis(), log);
        mReceLogAdapter.getDataList().add(0, logBean);
        mReceLogAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mManager != null) {
            mManager.disconnect();
            mManager.unRegisterReceiver(adapter);
        }
    }
}
