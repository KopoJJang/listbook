package com.example.listbook.service;

import com.example.listbook.dto.ListbookDTO;
import com.example.listbook.dto.PageRequestDTO;
import com.example.listbook.dto.PageResultDTO;
import com.example.listbook.entity.Listbook;
import com.example.listbook.entity.QListbook;
import com.example.listbook.repository.ListbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ListbookServiceImpl implements ListbookService{

    private final ListbookRepository repository;


    @Override
    public Long register(ListbookDTO dto){
        Listbook entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getBno();
    }

    @Override
    public PageResultDTO<ListbookDTO, Listbook> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Listbook> result = repository.findAll(booleanBuilder, pageable);
        Function<Listbook, ListbookDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ListbookDTO read(Long bno){
        Optional<Listbook> result = repository.findById(bno);
        return result.isPresent() ? entityToDTO(result.get()): null;
    }

    @Override
    public void modify(ListbookDTO dto) {
        Optional<Listbook> result = repository.findById(dto.getBno());
        if(result.isPresent()){
            Listbook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeWriter(dto.getWriter());
            entity.changeContent(dto.getContent());
            entity.changePublisher(dto.getPublisher());
            entity.changePrice(dto.getPrice());

            repository.save(entity);
        }
    }

    @Override
    public void remove(Long bno) {repository.deleteById(bno);}


    @Override
    public BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keword = requestDTO.getKeyword();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QListbook qListbook = QListbook.listbook;
        BooleanExpression expression = qListbook.bno.gt(0L);

        booleanBuilder.and(expression);


        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }


        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qListbook.title.contains(keword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qListbook.content.contains(keword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qListbook.writer.contains(keword));
        }


        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }


}
