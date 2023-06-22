package com.example.listbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Listbook extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(length = 1500, nullable = false)
    private  String content;

    @Column(length = 50, nullable = false)
    private String publisher;

    @Column(nullable = false)
    private int price;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeWriter(String writer){
        this.writer = writer;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void changePublisher(String publisher){
        this.publisher = publisher;
    }

    public void changePrice(int price){
        this.price = price;
    }

}
