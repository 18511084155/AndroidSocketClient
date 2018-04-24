package com.woodys.android.oksocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.woodys.android.oksocket.adapter.LogAdapter;
import com.woodys.android.oksocket.data.HandShake;
import com.woodys.android.oksocket.data.LogBean;
import com.woodys.android.oksocket.data.NearCarRegisterRq;
import com.woodys.android.oksocket.data.PulseBean;
import com.woodys.libsocket.sdk.ConnectionInfo;
import com.woodys.libsocket.sdk.OkSocket;
import com.woodys.libsocket.sdk.OkSocketOptions;
import com.woodys.libsocket.sdk.SocketActionAdapter;
import com.woodys.libsocket.sdk.bean.IPulseSendable;
import com.woodys.libsocket.sdk.bean.ISendable;
import com.woodys.libsocket.sdk.bean.OriginalData;
import com.woodys.libsocket.sdk.connection.IConnectionManager;

import java.nio.charset.Charset;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by didi on 2018/4/20.
 */

public class DemoActivity extends AppCompatActivity {

    private Button mSimpleBtn;

    private Button mComplexBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mSimpleBtn = (Button)findViewById(R.id.btn1);
        mComplexBtn = (Button)findViewById(R.id.btn2);


        mSimpleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoActivity.this, MainSimpleActivity.class);
                startActivity(intent);
            }
        });
        mComplexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

