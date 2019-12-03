package com.example.demo;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SortBy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ItemReposity itemReposity;

    @Test
    void aaa() {
        List<Item> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        Item item = new Item(1L, "小米手机list", " 手机",
                "小米", 34.00, "http://image.baidu.com/13123.jpg",dt1);
        Item item1 = new Item(2L, "小米手机list1", " 手机",
                "小米真的很不错啊", 3499.00, "http://image.baidu.com/13123.jpg",new Date());
        Item item2 = new Item(3L, "小米手机list1", " 手机",
                "测试", 33.00, "http://image.baidu.com/13123.jpg",new Date());
        Item item3 = new Item(4L, "小米手机list1", " 手机",
                "测试", 34.00, "http://image.baidu.com/13123.jpg",new Date());
        Item item4 = new Item(5L, "小米手机list1", " 手机",
                "小米", 499.00, "http://image.baidu.com/13123.jpg",new Date());
        list.add(item);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        itemReposity.saveAll(list);
        Iterable<Item> itemList = this.itemReposity.findByPriceBetween("499.00","1499.00");
        for(Item it:itemList){
            System.out.println(it);
        }
        System.out.println("执行成功");
    }
    @Test
    public  void search(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //完全匹配
        query.withQuery(QueryBuilders.termQuery("title.fullText","小米"));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search0(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //完全匹配 可以匹配多个字段
        query.withQuery(QueryBuilders.termsQuery("title","小米"));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search1(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //这个会分词查询  前提是有分词器 和通配符并不一样
        // ik_smart: 会做最粗粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国,国歌”。
        //ik_max_word: 会将文本做最细粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国,中华人民,中华,华人,人民共和国,人民,人,民,共和国,共和,和,国国,国歌”，会穷尽各种可能的组合；
        //standard 默认分词器会一个字一个字的拆分 比如会将“中华人民共和国国歌”拆分中，华，人，名 存入倒排索引List 转为list小写
        //重点来了 matchQuery和termQuery的区别 是在查询前先把查询条件给分掉 也按默认分 默认分词器和上面一个字一个字匹配只要返回相同的字就返回
        //所以说这个意义不大还是termQueyy好
       query.withQuery(QueryBuilders.matchQuery("brand","小米真的很不错啊"));
       //query.withQuery(QueryBuilders.multiMatchQuery("title","小米手机"));
        //完全匹配
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search2(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //按条件查询must等于And mustNot等于 != 还有个should 相当于or 还有boolQuery是必须要加的
       query.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("brand","测试")).mustNot(QueryBuilders.termQuery("price","33")));
       //query.withQuery(QueryBuilders.multiMatchQuery("title","小米手机"));
        //完全匹配
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search3(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //很明显只查询指定id的
       query.withQuery(QueryBuilders.idsQuery().addIds("1"));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search4(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //前缀查询
       query.withQuery(QueryBuilders.prefixQuery("title","小米"));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search5(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //通配符查询
       query.withQuery(QueryBuilders.wildcardQuery("brand","小*"));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search6(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //模糊查询分词 我也不知道啥
       query.withQuery(QueryBuilders.fuzzyQuery("brand","小米"));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }
    @Test
    public  void search7(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //基于内容的推荐我也不知道啊没试验出来
       query.withQuery(QueryBuilders.moreLikeThisQuery(new String[]{"title"},new String[]{"小米手机list"},null));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }


    //分页排序
    @Test
    public  void search8(){
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        //完全匹配 可以匹配多个字段
        query.withQuery(QueryBuilders.termsQuery("title","小米手机list","小米手机list1"));
        int page = 0;
        int pageSize = 2;
        Sort sort = Sort.by(Sort.Direction.DESC,"title");
        //或者也可以
       // query.withSort(SortBuilders.fieldSort("title").order(SortOrder.DESC));
        query.withPageable(PageRequest.of(page,pageSize,sort));
        Page<Item> items = this.itemReposity.search(query.build());
        long tota1 = items.getTotalElements();
        for(Item it:items){
            System.out.println(it);
        }
    }

}
