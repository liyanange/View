package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "index1",type = "type1")
public class Item
{
    @Id
    private  Long id;
    @Field(type = FieldType.Keyword)
    private  String title;
    @Field(type = FieldType.Keyword)
    private  String categroy;
    @Field(type = FieldType.Keyword)
    private  String brand;
    @Field(type = FieldType.Double)
    private  Double price;
    //默认都会建立索引
    @Field(index = false,type = FieldType.Keyword)
    private  String images;

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

    public Item(Long id, String title, String categroy, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.categroy = categroy;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }
}
