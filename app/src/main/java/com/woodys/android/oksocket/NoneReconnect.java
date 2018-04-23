package com.woodys.android.oksocket;

import android.content.Context;

import com.woodys.libsocket.sdk.ConnectionInfo;
import com.woodys.libsocket.sdk.connection.AbsReconnectionManager;

/**
 * Created by woodys 2018/3/24.
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
