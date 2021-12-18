package com.libokai.service.impl;

import com.libokai.mapper.ArticleMapper;
import com.libokai.mapper.FieldMapper;
import com.libokai.mapper.UserMapper;
import com.libokai.pojo.Article;
import com.libokai.pojo.Field;
import com.libokai.pojo.User;
import com.libokai.service.myService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class OtherApiServiceImpl implements ApplicationRunner, myService {
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    String todayGamesResult=null;

    public String updateApiData() throws IOException {
        String address = "http://apis.juhe.cn/fapig/nba/query?key=2a4fcde1592b5354ee66fa55af0d4886";
        URL url=new URL(address);
        String result="";
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();//创建网页连接
        int rCode=urlCon.getResponseCode();//测试连接情况
        if(rCode==200){   //rCode 返回两百表示成功
            System.out.println("connect successfully...");
            System.out.println("begin crawling1...");//开始本轮爬取
            BufferedReader reader=new BufferedReader
                    (new InputStreamReader(urlCon.getInputStream()));//创建输入流
            String line;
            while((line=reader.readLine())!=null){//逐行读取
                result+=line;
            }
        }
        else
        {
            return "赛果查询失败";
        }
        this.todayGamesResult = result;
        return "赛果查询成功";
    }

    public String getApiData(){
        return this.todayGamesResult;
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                new OtherApiServiceImpl().updateApiData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(this.updateApiData());

        Calendar calendar = Calendar.getInstance();
        /*** 定制每日12:00执行方法 ***/
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        if (date.before(new Date())) {
            Calendar startDT = Calendar.getInstance();
            startDT.setTime(date);
            startDT.add(Calendar.DAY_OF_MONTH, 1);
            date = startDT.getTime();
        }
        Timer timer = new Timer();
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(task, date, PERIOD_DAY);
    }
}
