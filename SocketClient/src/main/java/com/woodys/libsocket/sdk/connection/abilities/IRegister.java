package com.woodys.libsocket.sdk.connection.abilities;

import android.content.BroadcastReceiver;

import com.woodys.libsocket.sdk.connection.interfacies.ISocketActionListener;

/**
 * Created by woodys on 2017/4/17.
 */

public interface IRegister {
    void registerReceiver(BroadcastReceiver broadcastReceiver, String... action);

    void registerReceiver(final ISocketActionListener socketResponseHandler);

    void unRegisterReceiver(BroadcastReceiver broadcastReceiver);

    void unRegisterReceiver(ISocketActionListener socketResponseHandler);



}
