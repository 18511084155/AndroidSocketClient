package com.woodys.libsocket.sdk.connection;

import android.content.Context;

import com.woodys.libsocket.sdk.ConnectionInfo;

/**
 * Created by woodys on 2017/10/24.
 */

public class NoneReconnect extends AbsReconnectionManager {
    @Override
    public void onSocketDisconnection(Context context, ConnectionInfo info, String action, Exception e) {

    }

    @Override
    public void onSocketConnectionSuccess(Context context, ConnectionInfo info, String action) {

    }

    @Override
    public void onSocketConnectionFailed(Context context, ConnectionInfo info, String action, Exception e) {

    }
}
