package com.randymcbride.conferencedemo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity(name="pricing_categories")
public class PricingCategory {
    @Id
    @Column(name="pricing_category_code")
    private String code;
    @Column(name="pricing_category_name")
    private String name;
    @Column(name="pricing_start_date")
    private Date startDate;
    @Column(name="pricing_end_date")
    private Date endDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean includesDate(Date date) {
        return !(startDate.after(date) || endDate.before(date));
    }
}
