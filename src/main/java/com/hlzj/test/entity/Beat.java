package com.hlzj.test.entity;

import java.io.Serializable;
import java.util.Date;

public class Beat implements Serializable {
    private Integer id;

    private String beatdata;

    private Date cdate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeatdata() {
        return beatdata;
    }

    public void setBeatdata(String beatdata) {
        this.beatdata = beatdata == null ? null : beatdata.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}