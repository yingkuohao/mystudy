package com.mylearn.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: hh
 * Date: 11-9-22
 * Time: ????2:55
 * To change this template use File | Settings | File Templates.
 */
public class ViewFields  implements Serializable {
    private  int id;
    private String fieldDisplay;
    private  int display;
    private int typesBelong;
    private Date created;
    private Date modified;

    public ViewFields() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldDisplay() {
        return fieldDisplay;
    }

    public void setFieldDisplay(String fieldDisplay) {
        this.fieldDisplay = fieldDisplay;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getTypesBelong() {
        return typesBelong;
    }

    public void setTypesBelong(int typesBelong) {
        this.typesBelong = typesBelong;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }


}
