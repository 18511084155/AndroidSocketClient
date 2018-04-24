package com.woodys.libsocket.sdk.connection.abilities;

/**
 * Created by woodys on 2017/5/16.
 */

public interface IDisConnectable {
    void disconnect(Exception e);

    void disconnect();
}
