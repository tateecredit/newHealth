package com.ts.ssm.backrun;


import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by ts on 2016/12/26.
 */
public class Run implements Runnable{


    public void run() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        while (true){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("休息一会儿---"+ new java.util.Date(System.currentTimeMillis()));
        }


    }


}

