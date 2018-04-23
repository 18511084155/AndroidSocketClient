package com.woodys.libsocket.impl.abilities;

import com.woodys.libsocket.sdk.OkSocketOptions;
import com.woodys.libsocket.sdk.bean.ISendable;

/**
 * Created by woodys on 2017/4/16.
 */

public interface IIOManager {
    void resolve();

    void setOkOptions(OkSocketOptions options);

    void send(ISendable sendable);

    void close();

}
