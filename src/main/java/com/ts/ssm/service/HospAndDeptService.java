package com.ts.ssm.service;

import com.ts.ssm.model.HospAndDept;

import java.util.List;

/**
 * Created by ts on 2016/12/27.
 */
public interface HospAndDeptService {

    public List<HospAndDept> getHospAndDept(String deptname,String areaName);
}

