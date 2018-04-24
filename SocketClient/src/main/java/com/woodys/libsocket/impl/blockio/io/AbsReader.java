package com.woodys.libsocket.impl.blockio.io;

import android.support.annotation.MainThread;

import com.woodys.libsocket.impl.abilities.IReader;
import com.woodys.libsocket.sdk.OkSocketOptions;
import com.woodys.libsocket.sdk.connection.abilities.IStateSender;

import java.io.InputStream;

/**
 * Created by woodys on 2017/12/26.
 */

public abstract class AbsReader implements IReader {

    protected OkSocketOptions mOkOptions;

    protected IStateSender mStateSender;

    protected InputStream mInputStream;

    public AbsReader(InputStream inputStream, IStateSender stateSender) {
        mStateSender = stateSender;
        mInputStream = inputStream;
    }

    @Override
    @MainThread
    public void setOption(OkSocketOptions option) {
        mOkOptions = option;
    }
}
