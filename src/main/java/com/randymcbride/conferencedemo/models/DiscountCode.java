package com.randymcbride.conferencedemo.models;

import javax.persistence.*;
import java.util.Currency;

@Entity(name="discount_codes")
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="discount_code_id")
    private Long discountCodeId;

    @Column(name="discount_code")
    private String discountCode;

    @Column(name="discount_name")
    private String discountName;

    @Column(name="discount_type")
    private String discountType;

    @Column(name="discount_amount")
    private Currency discountAmount;

    public Long getDiscountCodeId() {
        return discountCodeId;
    }

    public void setDiscountCodeId(Long discountCodeId) {
        this.discountCodeId = discountCodeId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Currency getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Currency discountAmount) {
        this.discountAmount = discountAmount;
    }
}
