package com.woodys.libsocket.impl.abilities;

import com.woodys.libsocket.sdk.ConnectionInfo;
import com.woodys.libsocket.sdk.connection.IConnectionManager;

/**
 * Created by woodys on 2017/6/30.
 */

public interface IConnectionSwitchListener {
    void onSwitchConnectionInfo(IConnectionManager manager, ConnectionInfo oldInfo, ConnectionInfo newInfo);
}
