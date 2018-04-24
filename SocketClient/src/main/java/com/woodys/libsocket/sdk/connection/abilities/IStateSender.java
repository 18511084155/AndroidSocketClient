package com.woodys.libsocket.sdk.connection.abilities;

import java.io.Serializable;

/**
 * Created by woodys on 2017/5/17.
 */

public interface IStateSender {
    void sendBroadcast(String action, Serializable serializable);

    void sendBroadcast(String action);
}
