package com.example.listbook.repository;

import com.example.listbook.entity.Listbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ListbookRepositoryTests {
    @Autowired
    private ListbookRepository listbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(301,400).forEach(i -> {
            Listbook listbook = Listbook.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer("Writer... " + (i % 10))
                    .publisher("Publisher..."  + i)
                    .price(25000)
                    .build();
            System.out.println(listbookRepository.save(listbook));
        });
    }


}
