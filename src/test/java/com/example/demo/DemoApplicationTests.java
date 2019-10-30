package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ItemReposity itemReposity;

    @Test
    void aaa() {
        Item item = new Item(null, "小米手机10", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        item =itemReposity.save(item);
        System.out.println( item.toString());

    }

}
