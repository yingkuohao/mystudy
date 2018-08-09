package com.mylearn.domain;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: hh
 * Date: 11-9-23
 * Time: ????2:56
 * To change this template use File | Settings | File Templates.
 */
public class PageVo {

    /* private String number;

   public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private ViewFields viewFields;
    private EbokOrg ebokOrg;

    public ViewFields getViewFields() {
        return viewFields;
    }

    public void setViewFields(ViewFields viewFields) {
        this.viewFields = viewFields;
    }

    public EbokOrg getEbokOrg() {
        return ebokOrg;
    }

    public void setEbokOrg(EbokOrg ebokOrg) {
        this.ebokOrg = ebokOrg;
    }

    public PageVo() {
    }*/

    private  String check;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    private int id;
    private String org_name;
    /**??String??????**/
    private String org_type;
    private int status;
    private Date created_o;
    private Date modified_o;

    private String fieldDisplay;
    private  int display;

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

    public String getOrg_type() {
        return org_type;
    }

    public void setOrg_type(String org_type) {
        this.org_type = org_type;
    }
/*
    public int getOrg_type() {
        return org_type;
    }

    public void setOrg_type(int org_type) {
        this.org_type = org_type;
    }*/

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_o() {
        return created_o;
    }

    public void setCreated_o(Date created_o) {
        this.created_o = created_o;
    }

    public Date getModified_o() {
        return modified_o;
    }

    public void setModified_o(Date modified_o) {
        this.modified_o = modified_o;
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

    public Date getCreated_v() {
        return created_v;
    }

    public void setCreated_v(Date created_v) {
        this.created_v = created_v;
    }

    public Date getModified_v() {
        return modified_v;
    }

    public void setModified_v(Date modified_v) {
        this.modified_v = modified_v;
    }

    private int typesBelong;
    private Date created_v;

    public PageVo() {
    }

    private Date modified_v;

}
