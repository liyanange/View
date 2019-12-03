package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

@Document(indexName = "indexdate",type = "datet")
public class Item
{
    @Id
    private  Long id;
    @Field(type = FieldType.Keyword)
    private  String title;
     //啥都不写默认映射两个一个text分词 一个keyword不分词
    private  String categroy;
    //text会分词建立索引 默认分词器 一个字一个字的分
    @Field(type = FieldType.Keyword)
    private  String brand;
    @Field(type = FieldType.Double)
    private  Double price;
    //默认都会建立索引
    @Field(index = false,type = FieldType.Keyword)
    private  String images;
    @Field(type = FieldType.Date, format = DateFormat.custom,pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date publishDate;
    @Field(type = FieldType.Date)
    private Date createDate;
    private Date updateDate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Item() {
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Item(Long id, String title, String categroy, String brand, Double price, String images, Date publishDate) {
        this.id = id;
        this.title = title;
        this.categroy = categroy;
        this.brand = brand;
        this.price = price;
        this.images = images;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", categroy='" + categroy + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", images='" + images + '\'' +
                '}';
    }
}
