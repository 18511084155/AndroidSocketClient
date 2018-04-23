package com.woodys.libsocket.sdk.connection.abilities;

/**
 * Created by woodys on 2017/4/16.
 */

public interface IDisConnectable {
    void disConnect(Exception e);

    void disConnect();
}
