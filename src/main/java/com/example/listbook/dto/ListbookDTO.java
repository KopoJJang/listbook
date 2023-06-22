package com.example.listbook.dto;

import com.example.listbook.entity.Listbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListbookDTO {
    private Long bno;
    private String title;
    private String writer;
    private String content;
    private String publisher;
    private int price;
    private LocalDateTime regDate, modDate;
}
