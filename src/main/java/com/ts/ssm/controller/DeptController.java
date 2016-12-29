package com.ts.ssm.controller;


import com.ts.ssm.dao.OrderParser;
import com.ts.ssm.model.DeptBasicMsg;
import com.ts.ssm.model.DocBean;
import com.ts.ssm.model.HospAndDept;
import com.ts.ssm.service.DeptBasicMsgService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ts on 2016/12/26.
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    private Logger log = Logger.getLogger(DeptController.class);
    @Resource
    private DeptBasicMsgService deptBasicMsgService;

    @RequestMapping("/showDept")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有部门信息");
        List<DeptBasicMsg> deptList = deptBasicMsgService.getAlldeptBasicMsg();

        model.addAttribute("deptList",deptList);
        return "showDept";
    }

}
