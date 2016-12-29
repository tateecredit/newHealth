package com.ts.ssm.service.impl;

import com.ts.ssm.controller.UserController;
import com.ts.ssm.dao.HospAndDeptDao;
import com.ts.ssm.model.HospAndDept;
import com.ts.ssm.model.HospitalDetial;
import com.ts.ssm.service.HospAndDeptService;
import com.ts.ssm.service.HospitalDetialService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ts on 2016/12/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HospAndDeptServiceImpl implements HospAndDeptService{
    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private HospAndDeptDao hospAndDeptDao;

    @Override
    public List<HospAndDept> getHospAndDept(String deptname, String areaName) {
        return hospAndDeptDao.selectHospAndDept(deptname,areaName);
    }
}
