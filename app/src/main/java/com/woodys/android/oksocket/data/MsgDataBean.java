package com.woodys.android.oksocket.data;

import com.woodys.libsocket.sdk.bean.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Created by woodys 2018/3/24.
 */

public class MsgDataBean implements ISendable {
    private String content = "";

    public MsgDataBean(String content) {
        this.content = content;
    }

    @Override
    public byte[] parse() {
        /*byte[] body = content.getBytes(Charset.defaultCharset());
        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(body.length);
        bb.put(body);
        return bb.array();*/
        return content.getBytes(Charset.defaultCharset());
    }
}
