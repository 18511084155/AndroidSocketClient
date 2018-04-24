package com.woodys.libsocket.sdk.connection;

import com.woodys.libsocket.impl.PulseManager;
import com.woodys.libsocket.sdk.ConnectionInfo;
import com.woodys.libsocket.sdk.connection.abilities.IConfiguration;
import com.woodys.libsocket.sdk.connection.abilities.IConnectable;
import com.woodys.libsocket.sdk.connection.abilities.IDisConnectable;
import com.woodys.libsocket.sdk.connection.abilities.IRegister;
import com.woodys.libsocket.sdk.connection.abilities.ISender;

/**
 * Created by woodys on 2017/5/16.
 */

public interface IConnectionManager extends
        IConfiguration,
        IConnectable,
        IDisConnectable,
        ISender<IConnectionManager>,
        IRegister {

    boolean isConnect();

    boolean isDisconnecting();

    PulseManager getPulseManager();

    void setIsConnectionHolder(boolean isHold);

    ConnectionInfo getConnectionInfo();

    void switchConnectionInfo(ConnectionInfo info);

    AbsReconnectionManager getReconnectionManager();

}

