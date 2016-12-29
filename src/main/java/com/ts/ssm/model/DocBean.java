package com.ts.ssm.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by ts on 2016/12/28.
 */
public class DocBean {

    private String docId;
    private String docName;
    private String docPosition;
    private String hospitalId;
    private String hospitalName;
    private String deptId;
    private String deptName;
    private String sex;
    private LinkedList<LinkedHashMap<String,String>> orderList=new LinkedList<>();

    public LinkedList<LinkedHashMap<String, String>> getOrderList() {
        return orderList;
    }
    public LinkedHashMap<String,String> setOrderMap(String date, String order) {
        LinkedHashMap<String, String> orderMap = new LinkedHashMap<>();//introduce save date:x intro=y
        orderMap.put(date, order);
        return orderMap;
    }
    public void setOrderList(LinkedHashMap<String,String> orderMap) {
        orderList.add(orderMap);
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDocPosition() {
        return docPosition;
    }

    public void setDocPosition(String docPosition) {
        this.docPosition = docPosition;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
