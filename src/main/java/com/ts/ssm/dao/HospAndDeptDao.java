package com.ts.ssm.dao;

import com.ts.ssm.model.HospAndDept;

import java.util.List;

/**
 * Created by ts on 2016/12/27.
 */
public interface HospAndDeptDao {

    List<HospAndDept> selectHospAndDept(String deptname,String areaName);
}
