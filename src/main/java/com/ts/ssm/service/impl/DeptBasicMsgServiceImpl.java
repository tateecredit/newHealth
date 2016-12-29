package com.ts.ssm.service.impl;

import com.ts.ssm.dao.DeptBasicMsgDao;
import com.ts.ssm.model.DeptBasicMsg;
import com.ts.ssm.service.DeptBasicMsgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ts on 2016/12/26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptBasicMsgServiceImpl implements DeptBasicMsgService{

    @Resource
    private DeptBasicMsgDao deptBasicMsgDao;


    public List<DeptBasicMsg> getAlldeptBasicMsg() {
        return deptBasicMsgDao.selectAlldeptBasicMsg();
    }
}

