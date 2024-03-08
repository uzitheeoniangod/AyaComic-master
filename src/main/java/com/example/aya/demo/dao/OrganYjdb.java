package com.example.aya.demo.dao;

import java.io.Serializable;

public class OrganYjdb implements Serializable {
    private String type;
    private String organName;
    private String organId;
    private String empName;
    private String empId;
    private String num;
    private String areaName;
    private String hosiptialType;
    private String hosiptialDate;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getHosiptialType() {
        return hosiptialType;
    }

    public void setHosiptialType(String hosiptialType) {
        this.hosiptialType = hosiptialType;
    }

    public String getHosiptialDate() {
        return hosiptialDate;
    }

    public void setHosiptialDate(String hosiptialDate) {
        this.hosiptialDate = hosiptialDate;
    }

    public OrganYjdb(){}

    public OrganYjdb(String type, String organName, String organId, String empName, String empId, String num,String areaName, String hosiptialType, String hosiptialDate) {
        this.type = type;
        this.organName = organName;
        this.organId = organId;
        this.empName = empName;
        this.empId = empId;
        this.num = num;
        this.hosiptialType = hosiptialType;
        this.hosiptialDate = hosiptialDate;
        this.areaName=areaName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "OrganYjdb{" +
                "type='" + type + '\'' +
                ", organName='" + organName + '\'' +
                ", organId='" + organId + '\'' +
                ", empName='" + empName + '\'' +
                ", empId='" + empId + '\'' +
                ", num='" + num + '\'' +
                ", hosiptialType='" + hosiptialType + '\'' +
                ", hosiptialDate='" + hosiptialDate + '\'' +
                '}';
    }
}
