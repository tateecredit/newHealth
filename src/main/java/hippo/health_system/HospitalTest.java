package hippo.health_system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hippo.health_system.bean.DeptBean;
import hippo.health_system.bean.DocBean;
import hippo.health_system.bean.HospitalBean;
import hippo.health_system.database.DBConnector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by huangchaoyuan on 16/12/6.
 */
public class HospitalTest {
    private static List<String> regionList = new LinkedList<String>();

    private static void regionInit() {
        regionList.add("9571");
        regionList.add("0571");//hz
        regionList.add("0574");//nb
        regionList.add("0577");//wz
        regionList.add("0573");//jx
        regionList.add("0572");//hz
        regionList.add("0575");//sx
        regionList.add("0579");//jh
        regionList.add("0570");//qz
        regionList.add("0580");//zs
        regionList.add("0576");//tz
        regionList.add("1579");//yw
        regionList.add("0578");//ls
    }

    public static void main(String[] args) throws IOException {
        DBConnector.connect("root","root");
//        msgSave();//save data into mysql
        long begin = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(getOrderArray("肿瘤")));
        long end =System.currentTimeMillis();
        long result =end-begin;
        System.out.println(result+"ms");
        DBConnector.close();
    }


    private static void msgSave() {
        regionInit();
        for (String region : regionList) {
            HospitalParser hospitalParser = new HospitalParser();
            for (HospitalBean hospitalBean : hospitalParser.hospitalParse(region)) {
                System.out.println("hospital name: " + hospitalBean.getHospitalName());//know run where
                DBConnector.testInsertDetail(hospitalBean);
                DeptParser deptParser = new DeptParser();
                for (DeptBean deptBean : deptParser.deptParse(hospitalBean)) {
                    DBConnector.testInsertMain(hospitalBean, deptBean);
                }
            }
        }

    }
    private static JSONArray getOrderArray(String sick) throws IOException {
        OrderParser orderParser = new OrderParser();
        JSONArray array=new JSONArray();
        for (String id : DBConnector.getDeptIdByDeptName(sick)) {
            long begin = System.currentTimeMillis();

            List<DocBean> docBeans = orderParser.doctorParse(id);
            for (DocBean docBean : docBeans) {
                JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(docBean));
                array.add(jsonObject);
            }
            long end = System.currentTimeMillis();
            System.out.println(end-begin+"ms  ");


        }
        return array;
    }




}
