package com.ts.ssm.dao;

import org.springframework.stereotype.Repository;
import com.ts.ssm.model.DeptBasicMsg;

import java.util.List;

/**
 * Created by ts on 2016/12/26.
 */
@Repository
public interface DeptBasicMsgDao {

    List<DeptBasicMsg> selectAlldeptBasicMsg();
}
