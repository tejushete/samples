package com.example.studentbiodata;

import android.util.Log;

/**
 * Created by Tejashree on 26-Apr-17.
 */

public class reference {
    private String name;
    private String companyName;
    private String contactNo;
    private String knownYears;
    private String emailId;

    reference(){
        name = "";
        companyName = "";
        Log.d("TAG", "<><>initialized");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getKnownYears() {
        return knownYears;
    }

    public void setKnownYears(String knownYears) {
        this.knownYears = knownYears;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
