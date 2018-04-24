package com.woodys.libsocket.sdk.connection.abilities;

import com.woodys.libsocket.sdk.OkSocketOptions;
import com.woodys.libsocket.sdk.connection.IConnectionManager;

/**
 * Created by woodys on 2017/5/16.
 */

public interface IConfiguration {
    IConnectionManager option(OkSocketOptions okOptions);

    OkSocketOptions getOption();
}
