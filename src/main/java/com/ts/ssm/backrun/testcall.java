package com.ts.ssm.backrun;

import com.ts.ssm.dao.OrderParser;
import com.ts.ssm.model.DocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ts on 2016/12/28.
 */
public class testcall {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //创建两个有返回值的任务
        List<String> oids = new ArrayList<String>();
        oids.add("05700120767");
        oids.add("05700120768");
        oids.add("05700120769");
        List<DocBean> docBeanList = new ArrayList<DocBean>();
        for (int i =0;i<oids.size();i+=2){

            if(i+1<oids.size()){

                Callable c1 = new MyCallable(oids.get(i));
                Callable c2 = new MyCallable(oids.get(i+1));
                //执行任务并获取Future对象
                Future<List<DocBean>> f1 = pool.submit(c1);
                Future<List<DocBean>> f2 = pool.submit(c2);

                docBeanList.addAll(f1.get());
                docBeanList.addAll(f2.get());

            }else{
                Callable c3 = new MyCallable(oids.get(i));
                Future<List<DocBean>> f3 = pool.submit(c3);

                docBeanList.addAll(f3.get());
            }



        }
        for (int i =0;i<docBeanList.size();i++){
            System.out.println(docBeanList.get(i).getOrderList());

        }
        pool.shutdown();

    }

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
