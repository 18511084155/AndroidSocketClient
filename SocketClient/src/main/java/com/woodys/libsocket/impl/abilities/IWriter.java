package com.woodys.libsocket.impl.abilities;

import com.woodys.libsocket.sdk.OkSocketOptions;
import com.woodys.libsocket.sdk.bean.ISendable;

/**
 * Created by woodys on 2017/5/16.
 */

public interface IWriter {
    boolean write() throws RuntimeException;

    void setOption(OkSocketOptions option);

    void offer(ISendable sendable);

    int queueSize();

}
