package hippo.health_system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hippo.health_system.bean.HospitalBean;

import java.util.*;

/**
 * Created by huangchaoyuan on 16/12/5.
 */
public class HospitalParser {
    private final static String normal = new String("http://guahao.zjol.com.cn/list/hospital/");//hid hname byareaId
    private static HashMap<String, HospitalBean> hospitalMap = new HashMap<>();//hid->hospitalBean
    private final static String head=new String("http://guahao.zjol.com.cn/list/getHospitalListData?area=");
    private final static String tail=new String ("&depname&shortname&type&level&quality&pageNo&pageSize&haschange=true");


    public List<HospitalBean> hospitalParse(String regionId) {
        List<HospitalBean> hospitalBeanList = new LinkedList<HospitalBean>();
        String normalUrl = normal + regionId;
        String detailUrl = head+regionId+tail;
        JSONArray normalList = JsonParser.getJsonArrayByUrlFromBody(normalUrl);
        JSONArray detailList = JsonParser.getJsonArrayByUrlForHospitalDetail(detailUrl);

        for (int i=0;i<detailList.size();i++){
            JSONObject data=detailList.getJSONObject(i);
            HospitalBean hospitalBean =new HospitalBean();
            hospitalBean.setAreaId(regionId);
            hospitalBean.setAreaName(data.getString("AREANAME"));
            hospitalBean.setHospitalAddr(data.getString("HOSPITALADDR"));
            hospitalBean.setHospitalId(data.getString("HOSPITALID"));
            hospitalBean.setHospitalLevel(data.getString("HOSPITALLEVEL"));
            hospitalBean.setHospitalName(data.getString("HOSPITALNAME"));
            hospitalBean.setHospitalQuality(data.getString("HOSPITALQUALITY"));
            hospitalBean.setHospitalTel(data.getString("HOSPITALTELE"));
            hospitalBean.setHospitalType(data.getString("HOSPITALTYPE"));
//            hospitalBean.getHospitalOrderscsNums(data.getInteger("ORDERSUCCESS"));
            hospitalBean.setAreaName(areaName(regionId));
            hospitalMap.put(hospitalBean.getHospitalId(), hospitalBean);//put to map

            hospitalBeanList.add(hospitalBean);
//            System.out.println("type"+ hospitalBean.getHospitalType());
//            System.out.println("level"+ hospitalBean.getHospitalLevel());
//            System.out.println("quality"+ hospitalBean.getHospitalQuality());
//            System.out.println("type"+ hospitalBean.getHospitalType());

        }

        for (int i = 0; i < normalList.size(); i++) {
            JSONObject data = normalList.getJSONObject(i);
            if (!hospitalMap.containsKey(data.getString("HOSPITALID"))) {
                HospitalBean hospitalBean = new HospitalBean();
                hospitalBean.setAreaName(areaName(regionId));
                hospitalBean.setAreaId(regionId);
                hospitalBean.setHospitalName(data.getString("HOSPITALNAME"));
                hospitalBean.setHospitalId(data.getString("HOSPITALID"));//set name&id
                hospitalMap.put(hospitalBean.getHospitalId(), hospitalBean);//put to map
                hospitalBeanList.add(hospitalBean);
            }
        }
        return hospitalBeanList;
    }

    private String areaName(String region){

        switch (region){
            case    "9571":return "省直";
            case  "0571" :return "杭州";
            case   "0574":return    "宁波";
            case    "0577":return   "温州";
            case    "0573":return   "嘉兴";
            case    "0572":return   "湖州";
            case    "0575":return   "绍兴";
            case    "0579":return   "金华";
            case    "0570":return   "衢州";
            case    "0580":return   "舟山";
            case    "0576":return   "台州";
            case    "0578":return   "丽水";
            case    "1579":return   "义乌";
        }
        return "error.";

    }


}
