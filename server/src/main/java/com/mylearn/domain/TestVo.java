package com.mylearn.domain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hh
 * Date: 11-9-27
 * Time: ????2:53
 * To change this template use File | Settings | File Templates.
 */
public class TestVo {
    private List<EbokOrg> ebokOrg;
    private List<ViewFields> viewFields;
    private String id;

    public String getId() {
        return id;
    }

    public TestVo() {
    }

    public void setId(String id) {
        this.id = id;

    }

    public List<EbokOrg> getEbokOrg() {
        return ebokOrg;
    }

    public void setEbokOrg(List<EbokOrg> ebokOrg) {
        this.ebokOrg = ebokOrg;
    }

    public List<ViewFields> getViewFields() {
        return viewFields;
    }

    public void setViewFields(List<ViewFields> viewFields) {
        this.viewFields = viewFields;
    }
}
