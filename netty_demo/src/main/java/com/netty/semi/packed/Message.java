package com.netty.semi.packed;

/***
 * 消息对象
 */
public class Message {
    private int length;
    private String body;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Message(int length, String body) {
        this.length = length;
        this.body = body;
    }
}
