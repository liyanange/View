package com.example.demo;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private RocketMQTemplate template;
    @GetMapping("/books")
    public ModelAndView books(){
        List<Book> books = new ArrayList<>();
        //至于为什么用这个MessageBuilder 完全是为了与消费者对应 消费者也用的Message(MessageExt)类型 只不过这个有时间戳
        //或者得到重试次数 以及许多参数当然你也可以直接写String 生产者重试次数是两次 有三种方式发送 同步 异步 和 one way
        //前两种都有返回结果SendResult 下面代码默认是同步 他也有SendResult 只不过藏在代码里 下面的topic:tag 是有讲究的在可视化界面
        //直观的看到了
      for (int i= 0; i<20; i++) {
          template.convertAndSend("topic:tag", "呵呵呵呵");
      }
        Book b1 = new Book();
        b1.setId(1);
        b1.setAuthor("李楠");
        b1.setName("火狐撒谎");
        List<String> list = new ArrayList<>();
        list.add("呵呵呵");
        list.add("嘿嘿");
        list.add("拉拉");
        b1.setList(list);
        books.add(b1);
        b1 = new Book();
        b1.setId(2);
        b1.setAuthor("嗯嗯嗯");
        b1.setName("但是");
        list.add("呵呵呵");
        list.add("嘿嘿");
        list.add("拉拉");
        b1.setList(list);
        books.add(b1);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books",b1);
        mv.setViewName("books");
        return  mv;

    }
}
