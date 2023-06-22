package com.example.listbook.repository;

import com.example.listbook.entity.Listbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ListbookRepository extends JpaRepository<Listbook, Long>, QuerydslPredicateExecutor<Listbook> {
}



