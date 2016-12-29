package com.ts.ssm.dao;


import com.ts.ssm.model.DocBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by ts on 2016/12/27.
 */
public class OrderParser {
    private static String[] date = new String[8];
    private static String time = null;
    private static int count = 0;

    public List<DocBean> doctorParse(String id) throws IOException {
//        String id=deptBean.getDeptId(); //if needed deptId
        String hid = id.substring(0, 6);
        String deptId = id.substring(6);
        Document document = Jsoup.connect(getOrderUrl(hid, deptId)).timeout(30000).get();
        return orderParse(document, id);
    }


    private List<DocBean> orderParse(Document doc, String id) {
        String hId=id.substring(0,6);
/* String hospitalId = hId; */
        List<DocBean> docBeanList = new LinkedList<DocBean>();
        HashMap<String, DocBean> docMap = new HashMap<>();//did->docBean
        Elements links = doc.select("div.kspb-content");//form (All)
        Elements deptNamelink=doc.select("span.hui3-30-30");//deptNameFlag
        Elements hospitalNamelink=doc.select("span.lv-14-30-2");//hospitalNameFlag
        Elements deptIntrolink=doc.select("div.ksjs");//deptIntroFlag
        Elements doctorLinks = links.select("div.kspb-doc-box");//docs
        Elements dateLinks = links.select("dd.fl").select("li");//date

        setDate(dateLinks);
        setDocBean(doctorLinks, id, docBeanList, docMap,deptNamelink,hospitalNamelink);
        return docBeanList;
    }

    public void setOrderMap(Elements orderLinks, DocBean docBean) {
        boolean realTime = true;
        for (Element orderlink : orderLinks) {
            if (realTime) time = "上午";
            else time = "下午";
            String liClass = orderlink.attr("class");
            String key =  date[count];
            LinkedHashMap<String,String>  map;
            switch (liClass) {
                case "kyy":
                    map=docBean.setOrderMap("date",key);
                    map.put(time+"order", orderlink.text() + " " + orderlink.attr("title"));
                    break;
                case "ym":
                    map=docBean.setOrderMap("date",key);
                    map.put(time+"order" ,orderlink.text() + " " + orderlink.attr("title"));
                    break;
                case "wyy":
                    map=docBean.setOrderMap("date",key);
                    map.put(time+"order",  "无法预约 " + orderlink.attr("title"));
                    break;
                case "tz":
                    map=docBean.setOrderMap("date",key);
                    map.put(time+"order" ,orderlink.text() + " " + orderlink.attr("title"));
                    break;
                default:
                    continue;
//                    map=docBean.setOrderMap("date",key);
//                    map.put(time+"order", "暂无排班");

            }
            docBean.setOrderList(map);

            count++;
            if (count > 7) {
                count = 0;
                realTime = !realTime;
            }


        }
    }

    private String getOrderUrl(String hid, String deptid) {

        final String orderHead = new String("http://guahao.zjol.com.cn/pb/");
        final String orderTail = new String("&fuzzy_deptId=0&docId=&fuzzy_docId=0");
        final String orderMid = new String("?deptId=");
        String url = orderHead + hid + orderMid + deptid + orderTail;
        return url;

    }

    private void setDate(Elements dateLinks) {
        for (Element e : dateLinks) {
            date[count] = e.text();
            count++;
            if (count > 7) count = 0;
        }//get date today
    }

    private List<DocBean> setDocBean(Elements doctorLinks, String id, List docBeanList, HashMap docMap,Elements deptName,Elements hospitalname) {
        String hospitalId =id.substring(0,6);
        String deptId=id.substring(6);
        for (Element doctorLink : doctorLinks) {
            DocBean docBean = new DocBean();
            String docName = doctorLink.select("span.blue2-16-22").text();//docname
            setPositionAndSex(doctorLink.select("span.hui3-13-22"), docBean);//docPosition&sex
            String docIntro = doctorLink.select("textarea").text();
            Elements orderLinks = doctorLink.select("ul.clearfix").select("li");//orderMsg
            String originId = doctorLink.select("a").attr("href").toString();
            String docId = getDocId(originId);
            setOrderMap(orderLinks, docBean);
            if (docId.length() == 0) {
                docId = "普通";
            }

            docId = hospitalId + docId;
            docBean.setHospitalId(hospitalId);
            docBean.setHospitalName(hospitalname.text().substring(3));
            docBean.setDocId(docId);
            docBean.setDocName(docName);
            docBean.setDeptId(deptId);
            docBean.setDeptName(deptName.text());
            docBeanList.add(docBean);
            docMap.put(docId, docBean);
//            System.out.println(docBean.getOrderMap());
        }
        return docBeanList;

    }

    private static void setPositionAndSex(Elements es, DocBean docBean) {
        boolean pOrS = true;
        for (Element e : es) {
            if (pOrS) {
                docBean.setDocPosition(e.text());
                pOrS = false;
            } else docBean.setSex(e.text());
        }
    }

    private static String getDocId(String originId) {
        String content = originId;
        String regex = "javascript:searchByType.getScheduleByDocID\\('([^']*)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        if (m.find()) {
            return m.group(1);
        }

        return "parse error";

    }//regexToGetIdFromHerf


}