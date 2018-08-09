package com.mylearn.domain;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: hh
 * Date: 11-9-23
 * Time: ????2:18
 * To change this template use File | Settings | File Templates.
 */
public class EbokOrg {
    private int id;
    private String org_name;
    private int org_type;
    private int status;
    private Date created;
    private Date modified;

    public EbokOrg() {
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public int getOrg_type() {
        return org_type;
    }

    public void setOrg_type(int org_type) {
        this.org_type = org_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
