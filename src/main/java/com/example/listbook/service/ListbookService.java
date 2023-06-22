package com.example.listbook.service;

import com.example.listbook.dto.ListbookDTO;
import com.example.listbook.dto.PageRequestDTO;
import com.example.listbook.dto.PageResultDTO;
import com.example.listbook.entity.Listbook;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;

public interface ListbookService {

    Long register(ListbookDTO dto);
    PageResultDTO<ListbookDTO, Listbook> getList(PageRequestDTO requestDTO);
    ListbookDTO read(Long bno);
    void modify(ListbookDTO dto);
    void remove(Long bno);

    default Listbook dtoToEntity(ListbookDTO dto){
        Listbook entity = Listbook.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .content(dto.getContent())
                .publisher(dto.getPublisher())
                .price(dto.getPrice())
                .build();
        return entity;
    }



    default ListbookDTO entityToDTO(Listbook entity){
        ListbookDTO dto = ListbookDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .writer(entity.getWriter())
                .content(entity.getContent())
                .publisher(entity.getPublisher())
                .price(entity.getPrice())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }

    BooleanBuilder getSearch(PageRequestDTO requestDTO);


}

