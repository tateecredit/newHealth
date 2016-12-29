package hippo.health_system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by huangchaoyuan on 16/12/5.
 */
public class JsonParser {
    public static String loadJson(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static JSONArray getJsonArrayByUrlFromBody(String url) {
        JSONArray dataList = getJsonObject(url).getJSONArray("body");
        return dataList;

    }//Object K is body get V(array(list)) K is body

    public static JSONObject getJsonObject(String url) {
        String jsondata = loadJson(url);
        JSONObject jsonObject = JSON.parseObject(jsondata);
        return jsonObject;
    }

    public static JSONArray getJsonArrayByUrlForHospitalDetail(String url){
        JSONArray dataList =getJsonObject(url).getJSONObject("body").getJSONArray("data_list");
        return dataList;
    }


    public static void map2Json(Map map){
        String json=JSON.toJSONString(map,true);
        System.out.println(json);
    }
}
