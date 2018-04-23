package com.woodys.libsocket.impl.blockio.threads;

import android.content.Context;

import com.woodys.libsocket.impl.LoopThread;
import com.woodys.libsocket.impl.abilities.IReader;
import com.woodys.libsocket.impl.abilities.IWriter;
import com.woodys.libsocket.sdk.connection.abilities.IStateSender;
import com.woodys.libsocket.sdk.connection.interfacies.IAction;
import com.woodys.libsocket.utils.SLog;

import java.io.IOException;

/**
 * Created by woodys on 2017/4/17.
 */

public class SimplexIOThread extends LoopThread {
    private IStateSender mStateSender;


    private IReader mReader;

    private IWriter mWriter;

    private boolean isWrite = false;


    public SimplexIOThread(Context context, IReader reader,
            IWriter writer, IStateSender stateSender) {
        super(context, "simplex_io_thread");
        this.mStateSender = stateSender;
        this.mReader = reader;
        this.mWriter = writer;
    }

    @Override
    protected void beforeLoop() throws IOException {
        mStateSender.sendBroadcast(IAction.ACTION_WRITE_THREAD_START);
        mStateSender.sendBroadcast(IAction.ACTION_READ_THREAD_START);
    }

    @Override
    protected void runInLoopThread() throws IOException {
        isWrite = mWriter.write();
        if (isWrite) {
            isWrite = false;
            mReader.read();
        }
    }

    @Override
    protected void loopFinish(Exception e) {
        if (e != null) {
            SLog.e("simplex error,thread is dead with exception:" + e.getMessage());
        }
        mStateSender.sendBroadcast(IAction.ACTION_WRITE_THREAD_SHUTDOWN, e);
        mStateSender.sendBroadcast(IAction.ACTION_READ_THREAD_SHUTDOWN, e);
    }
}
