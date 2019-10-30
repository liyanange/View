package com.example.demo;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DicTag implements TemplateDirectiveModel {
    private String METHOD_KEY="method";
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setId(1);
        b1.setAuthor("李楠");
        b1.setName("火狐撒谎");
        books.add(b1);
        b1 = new Book();
        b1.setId(2);
        b1.setAuthor("嗯嗯嗯");
        b1.setName("但是");
        books.add(b1);
//
//      templateModels[0] =new SimpleSequence(books, environment.getObjectWrapper());
//        environment.setVariable("tagList",  templateModels[0]);
//      templateDirectiveBody.render(environment.getOut());
        if (map.containsKey(METHOD_KEY)) {
            DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
            String method = map.get(METHOD_KEY).toString();
            switch (method) {
                case "tagList":
                    // 将数据对象转换成对应的TemplateModel
                    TemplateModel tm = builder.build().wrap(books);
                    environment.setVariable("tagsList", tm);
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());


    }
}
