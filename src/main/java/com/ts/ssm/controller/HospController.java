package com.ts.ssm.controller;

import com.ts.ssm.model.HospitalDetial;
import com.ts.ssm.service.HospitalDetialService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ts on 2016/12/26.
 */
@Controller
@RequestMapping("/hosp")
public class HospController {

    private Logger log = Logger.getLogger(DeptController.class);
    @Resource
    private HospitalDetialService hospitalDetialService;

    @RequestMapping("/showHosp")
    public String showHosp(HttpServletRequest request, Model model){

        log.info("查询所有部门信息");
        List<HospitalDetial> hospitalDetialList = hospitalDetialService.getAllHospitalDetial();
        model.addAttribute("hospitalDetialList",hospitalDetialList);
        return "showHosp";


    }
    @RequestMapping("/gethosplist")
    public String getHosplist(HttpServletRequest request, Model model){

        log.info("根据部门查询相关医院的信息");
        String dept = request.getParameter("dept");
        System.out.println(dept+"**********************************");
        List<HospitalDetial> hospitalDetialList = hospitalDetialService.getAllHospitalDetial();
        model.addAttribute("hospitalDetialList",hospitalDetialList);
        return "showHosp";


    }

}
