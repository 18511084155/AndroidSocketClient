package com.woodys.libsocket.impl.blockio.threads;

import android.content.Context;

import com.woodys.libsocket.impl.LoopThread;
import com.woodys.libsocket.impl.abilities.IReader;
import com.woodys.libsocket.sdk.connection.abilities.IStateSender;
import com.woodys.libsocket.sdk.connection.interfacies.IAction;
import com.woodys.libsocket.utils.SLog;

import java.io.IOException;

/**
 * Created by woodys on 2017/4/17.
 */

public class DuplexReadThread extends LoopThread {
    private IStateSender mStateSender;

    private IReader mReader;

    public DuplexReadThread(Context context, IReader reader, IStateSender stateSender) {
        super(context, "duplex_read_thread");
        this.mStateSender = stateSender;
        this.mReader = reader;
    }

    @Override
    protected void beforeLoop() {
        mStateSender.sendBroadcast(IAction.ACTION_READ_THREAD_START);
    }

    @Override
    protected void runInLoopThread() throws IOException {
        mReader.read();
    }

    @Override
    protected void loopFinish(Exception e) {
        if (e != null) {
            SLog.e("duplex read error,thread is dead with exception:" + e.getMessage());
        }
        mStateSender.sendBroadcast(IAction.ACTION_READ_THREAD_SHUTDOWN, e);
    }
}
