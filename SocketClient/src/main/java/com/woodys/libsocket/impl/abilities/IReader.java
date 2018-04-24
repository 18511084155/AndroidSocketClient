package com.woodys.libsocket.impl.abilities;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import com.woodys.libsocket.sdk.OkSocketOptions;

/**
 * Created by woodys on 2017/5/16.
 */

public interface IReader {

    @WorkerThread
    void read() throws RuntimeException;

    @MainThread
    void setOption(OkSocketOptions option);
}
