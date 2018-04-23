package com.woodys.libsocket.sdk.connection.abilities;

import com.woodys.libsocket.sdk.bean.ISendable;

/**
 * Created by woodys on 2017/4/16.
 */

public interface ISender<T> {
    T send(ISendable sendable);
}
