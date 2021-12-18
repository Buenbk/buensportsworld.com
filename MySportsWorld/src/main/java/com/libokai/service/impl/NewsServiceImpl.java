package com.libokai.service.impl;

import com.libokai.mapper.NewsMapper;
import com.libokai.pojo.News;
import com.libokai.service.myService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class NewsServiceImpl implements ApplicationRunner, myService {
    @Autowired
    private NewsMapper newsMapper;

    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    public int deleteByPrimaryKey(Integer id)
    {
        return newsMapper.deleteByPrimaryKey(id);
    }

    public int insert(News record)
    {
        return newsMapper.insert(record);
    }

    public News selectByPrimaryKey(Integer id)
    {
        return newsMapper.selectByPrimaryKey(id);
    }

    public List<News> selectAll()
    {
        return newsMapper.selectAll();
    }

    public int updateByPrimaryKey(News record)
    {
        return newsMapper.updateByPrimaryKey(record);
    }

    public void deleteAll()
    {
        newsMapper.deleteAll();
    }

    public String getNBANews() throws IOException {
        //获取jsoup对象并使用get方法获取html元素
        Document document = Jsoup.connect("https://sports.qq.com/nba/").get();
        //根据id获取元素内容
        Elements div = document.select(".col-left");
        //找到a连接的元素
        Elements a = div.select("li a");
        List<News> news = new ArrayList<>();

        boolean flag = true;
        for (Element element : a) {
            //因为a连接中间的内容就是文件标题
            String title = element.text();
            //文件名使用文章标题会有一些特殊字符  所以进行处理
            //title = title.replaceAll("[`~!@#-_$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】《》 ‘；：”“’。，、？\\\"]", "");
            if(flag)
            {
                List<News> newsDB = this.selectAll();

                if(!newsDB.isEmpty())
                {
                    //System.out.println(newsDB.get(0).getTitle());
                    //System.out.println(title);
                    if(title.equals(newsDB.get(0).getTitle()))
                    {
                        return "news重复";
                    }
                    else
                    {
                        this.deleteAll();
                    }
                }
            }
            flag=false;
            //获取文章详情的url地址
            String url = element.attr("href");
            //System.out.println(title+"---"+url);
            if(!url.equals("")){
                //因为有些网站会有反扒机制所以需要对地址没有加http的路径添加http
                if((!url.startsWith("http"))){
                    url = "http:"+url;
                }
                //获取文章详情的内容
                Document articleDiv = Jsoup.connect(url).get();
                //文章内容全在一个div里
                Elements details = articleDiv.select(".one-p");
                String articleContent = "";
                for (Element element2 : details) {
                    //System.out.println("here");
                    //获取文章内容
                    String content = element2.text();
                    articleContent+=content+"<br>";
                    //System.out.println(content);

                }
                //System.out.println(":--"+articleContent);
                if(!articleContent.equals(""))
                {
                    News n1 = new News(title, articleContent);
                    news.add(n1);
                    this.insert(n1);
                    //System.out.println("插入成功---"+n1);
                }
            }

        }
        //return news;
        return "更新数据库news成功";
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                new NewsServiceImpl().getNBANews();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(this.getNBANews());

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
