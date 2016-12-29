package hippo.health_system;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hippo.health_system.bean.DeptBean;
import hippo.health_system.bean.HospitalBean;

import java.util.*;

/**
 * Created by huangchaoyuan on 16/12/5.
 */
public class DeptParser {
    private final static String head = new String("http://guahao.zjol.com.cn/list/dept/");//deptid deptname byHid
    private static HashMap<String, DeptBean> deptMap = new HashMap<>();//did->deptBean


    private static String getUrl(String hospitalId) {
        String url = head + hospitalId;
        return url;
    }

    public static DeptBean getBean(String key){
        return deptMap.get(key);
    }

    public List<DeptBean> deptParse(HospitalBean hospitalBean) {
        String hospitalId = hospitalBean.getHospitalId();
        List<DeptBean> deptBeanList = new LinkedList<DeptBean>();
        JSONArray dataList = JsonParser.getJsonArrayByUrlFromBody(getUrl(hospitalId));
        JSONObject data;
        for (int i = 0; i < dataList.size(); i++) {
            data = dataList.getJSONObject(i);
            DeptBean deptBean = new DeptBean();
            if (!deptMap.containsKey(hospitalId + data.getString("DEPTHISCODE"))) {//remove repeat key:hid+deptId
                deptBean.setDeptId(hospitalId + data.getString("DEPTHISCODE"));
                deptBean.setDeptName(data.getString("DEPTNAME"));
                deptMap.put(deptBean.getDeptId(), deptBean);
                deptBeanList.add(deptBean);
            }
        }
        return deptBeanList;
    }
}
