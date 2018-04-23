package com.woodys.android.oksocket.data;

import com.woodys.libsocket.sdk.bean.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Created by woodys on 2017/4/22.
 */

public class HandShake implements ISendable {
    private String content = "";

    public HandShake() {
        content = "Hello I'm a OkSocket demo";
    }

    @Override
    public byte[] parse() {
        byte[] body = content.getBytes(Charset.defaultCharset());
        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(body.length);
        bb.put(body);
        return bb.array();
    }
}
