package com.ts.ssm.controller;

import com.ts.ssm.dao.OrderParser;
import com.ts.ssm.model.DocBean;
import com.ts.ssm.model.HospAndDept;
import com.ts.ssm.service.HospAndDeptService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * Created by ts on 2016/12/27.
 */
@Controller
@RequestMapping("/dept")
public class HospAndDeptController {

    private Logger log = Logger.getLogger(DeptController.class);
    @Resource
    private HospAndDeptService hospAndDeptService;

    @RequestMapping("/showHospAndDept")
    public String showHospAndDept(HttpServletRequest request, Model model) throws IOException, ExecutionException, InterruptedException {

        log.info("查询该种类科室的预约信息");
        String dept = request.getParameter("dept");
        String areaName = request.getParameter("selectbar");

        List<HospAndDept> hospAndDeptList = new ArrayList<HospAndDept>();
        hospAndDeptList = hospAndDeptService.getHospAndDept(dept,areaName);
        System.out.println("hospAndDeptList"+hospAndDeptList.size());

        List<String> deptidList = new ArrayList<String>();
        if(hospAndDeptList.size()>0){
            for(int i=0;i<hospAndDeptList.size();i++){
                String deptid = hospAndDeptList.get(i).getDeptId();
                deptidList.add(deptid);
            }
        }

        OrderParser orderParser = new OrderParser();
        List<DocBean> docBeanList = new ArrayList<DocBean>();
//        for (String id :deptidList){
//            // 产生一个任务，并将其加入到线程池
//            List<DocBean> docBeans = orderParser.doctorParse(id);
//            docBeanList.addAll(docBeans);
//        }
        ExecutorService pool = Executors.newFixedThreadPool(100);
        long start = System.currentTimeMillis();
        if (deptidList.size()>0){

            for(int i=0;i<deptidList.size();i+=3){

                if(i+2<deptidList.size()){

                    Callable c1 = new MyCallable(deptidList.get(i));
                    Callable c2 = new MyCallable(deptidList.get(i+1));
                    Callable c4 = new MyCallable(deptidList.get(i+2));
                    //执行任务并获取Future对象
                    Future<List<DocBean>> f1 = pool.submit(c1);
                    Future<List<DocBean>> f2 = pool.submit(c2);
                    Future<List<DocBean>> f4 = pool.submit(c4);

                    docBeanList.addAll(f1.get());
                    docBeanList.addAll(f2.get());
                    docBeanList.addAll(f4.get());

                }
//                else if(i+1<deptidList.size()){
//                    Callable c3 = new MyCallable(deptidList.get(i));
//                    Future<List<DocBean>> f3 = pool.submit(c3);
//                    Callable c5 = new MyCallable(deptidList.get(i+1));
//                    Future<List<DocBean>> f5 = pool.submit(c5);
//
//                    docBeanList.addAll(f3.get());
//                }else {
//                    Callable c6 = new MyCallable(deptidList.get(i));
//                    Future<List<DocBean>> f6 = pool.submit(c6);
//
//                    docBeanList.addAll(f6.get());
//
//                }


                List<DocBean> docBeans = orderParser.doctorParse(deptidList.get(i));
                docBeanList.addAll(docBeans);

            }
            long stop = System.currentTimeMillis();
            long time = stop-start;
            System.out.println("共计"+time);
        }

        model.addAttribute("docBeanList",docBeanList);
        return "showDeptAndHosp";
    }

    class MyCallable implements Callable<List<DocBean>>{
        private String oid;

        MyCallable(String oid) {
            this.oid = oid;
        }

        @Override
        public List<DocBean> call() throws Exception {
            OrderParser orderParser = new OrderParser();
            List<DocBean> docBeans = new ArrayList<DocBean>();
            docBeans = orderParser.doctorParse(oid);
            //System.out.println(docBeans.get(0).getOrderList());
            return docBeans;
        }
    }
}

