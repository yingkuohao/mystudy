package com.mylearn.j2ee.jmx.jvm.msg;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:51
 * CopyRight: taobao
 * Descrption:
 */

public class TLVMessage {

    // 类型:json,xml,binary等
    private short  type;
    // value的长度
    private int    length;

    private byte[] value;

    public TLVMessage(){
    }

    public TLVMessage(short type, int length, byte[] value){
        super();
        this.type = type;
        this.length = length;
        this.value = value;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

}

