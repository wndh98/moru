package com.mogumogu.moru.template.repository;

import com.mogumogu.moru.template.entity.TemplateFoodList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateFoodListRepository extends JpaRepository<TemplateFoodList,Integer> {

    TemplateFoodList deleteByTfNum(Integer tfNum);

    List<TemplateFoodList> findByTfNum(Integer tfNum);
}
