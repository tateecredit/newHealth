package com.ts.ssm.service.impl;

import com.ts.ssm.dao.HospitalDetialDao;
import com.ts.ssm.model.HospitalDetial;
import com.ts.ssm.service.HospitalDetialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ts on 2016/12/26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HospitalDetialServiceimpl implements HospitalDetialService{

    @Resource
    private HospitalDetialDao hospitalDetialDao;

    public List<HospitalDetial> getAllHospitalDetial() {

        return hospitalDetialDao.selectAllhospitaldetial();
    }
}
