package com.mogumogu.moru.template.repository;


import com.mogumogu.moru.template.entity.TemplateActiviteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateActiviteListRepository extends JpaRepository<TemplateActiviteList, Integer> {
    TemplateActiviteList deleteByTalNum(Integer talNum);
    List<TemplateActiviteList> findByTaNum(Integer taNum);
}
