package com.woodys.libsocket.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.woodys.libsocket.impl.abilities.IConnectionSwitchListener;
import com.woodys.libsocket.sdk.bean.IPulseSendable;
import com.woodys.libsocket.sdk.ConnectionInfo;
import com.woodys.libsocket.sdk.bean.ISendable;
import com.woodys.libsocket.sdk.bean.OriginalData;
import com.woodys.libsocket.sdk.connection.IConnectionManager;
import com.woodys.libsocket.sdk.connection.abilities.IStateSender;
import com.woodys.libsocket.sdk.connection.interfacies.ISocketActionListener;
import com.woodys.libsocket.utils.SocketBroadcastManager;

import java.io.Serializable;
import java.util.HashMap;

import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_CONNECTION_FAILED;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_CONNECTION_SUCCESS;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_DATA;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_DISCONNECTION;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_PULSE_REQUEST;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_READ_COMPLETE;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_READ_THREAD_SHUTDOWN;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_READ_THREAD_START;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_WRITE_COMPLETE;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_WRITE_THREAD_SHUTDOWN;
import static com.woodys.libsocket.sdk.connection.interfacies.IAction.ACTION_WRITE_THREAD_START;


/**
 * Created by woodys on 2017/5/17.
 */

public abstract class AbsConnectionManager implements IConnectionManager {
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 连接信息
     */
    protected ConnectionInfo mConnectionInfo;
    /**
     * 连接信息switch监听器
     */
    private IConnectionSwitchListener mConnectionSwitchListener;
    /**
     * 状态机
     */
    protected ActionDispatcher mActionDispatcher;

    public AbsConnectionManager(Context context, ConnectionInfo info) {
        mContext = context;
        mConnectionInfo = info;
        mActionDispatcher = new ActionDispatcher(mContext, info);
    }

    public void registerReceiver(BroadcastReceiver broadcastReceiver, String... action) {
        mActionDispatcher.registerReceiver(broadcastReceiver, action);
    }

    public void registerReceiver(final ISocketActionListener socketResponseHandler) {
        mActionDispatcher.registerReceiver(socketResponseHandler);
    }


    public void unRegisterReceiver(BroadcastReceiver broadcastReceiver) {
        mActionDispatcher.unRegisterReceiver(broadcastReceiver);
    }

    public void unRegisterReceiver(ISocketActionListener socketResponseHandler) {
        mActionDispatcher.unRegisterReceiver(socketResponseHandler);
    }

    protected void sendBroadcast(String action, Serializable serializable) {
        mActionDispatcher.sendBroadcast(action, serializable);
    }

    protected void sendBroadcast(String action) {
        mActionDispatcher.sendBroadcast(action);
    }

    @Override
    public ConnectionInfo getConnectionInfo() {
        if (mConnectionInfo != null) {
            return mConnectionInfo.clone();
        }
        return null;
    }

    @Override
    public void switchConnectionInfo(ConnectionInfo info) {
        if (info != null) {
            ConnectionInfo tempOldInfo = mConnectionInfo;
            mConnectionInfo = info.clone();
            if (mConnectionSwitchListener != null) {
                mConnectionSwitchListener.onSwitchConnectionInfo(this, tempOldInfo, mConnectionInfo);
            }
        }
    }

    protected void setOnConnectionSwitchListener(IConnectionSwitchListener listener) {
        mConnectionSwitchListener = listener;
    }
}
