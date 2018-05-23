package com.hlzj.test.entity;

import java.io.Serializable;
import java.util.Date;

public class Oxygen implements Serializable {
    private Integer id;

    private Double oxygendata;

    private Date cdate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getOxygendata() {
        return oxygendata;
    }

    public void setOxygendata(Double oxygendata) {
        this.oxygendata = oxygendata;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}