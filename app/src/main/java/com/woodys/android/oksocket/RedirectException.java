package com.woodys.android.oksocket;

import com.woodys.libsocket.sdk.ConnectionInfo;

/**
 * Created by woodys on 2017/3/30.
 */

public class RedirectException extends RuntimeException {
    public ConnectionInfo redirectInfo;

    public RedirectException(ConnectionInfo redirectInfo) {
        this.redirectInfo = redirectInfo;
    }
}
