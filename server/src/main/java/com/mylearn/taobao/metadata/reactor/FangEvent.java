package com.mylearn.taobao.metadata.reactor;

import java.io.Serializable;
import java.util.Date;
import reactor.event.Event;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/21
 * Time: 10:54
 * CopyRight: taobao
 * Descrption:
 */

public class FangEvent extends Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String source;

    private Date timestamp;

    private String message;

    Object o;

    public FangEvent(Object data) {
        super(data);
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LogEvent [source=" + source + ", timestamp=" + timestamp
                + ", message=" + message + "]";
    }

}
