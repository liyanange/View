package com.example.demo;

import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
@org.springframework.context.annotation.Configuration
public class FreemakerConfig  implements InitializingBean {
    @Autowired
    private Configuration configuration;
    @Autowired
    private DicTag dicTag;

    @Override
    public void afterPropertiesSet() throws Exception {
        configuration.setSharedVariable("dic",dicTag);

    }
}
