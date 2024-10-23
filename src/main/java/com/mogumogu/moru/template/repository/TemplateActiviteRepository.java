package com.mogumogu.moru.template.repository;

import com.mogumogu.moru.template.entity.TemplateActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateActiviteRepository extends JpaRepository<TemplateActivite, Integer> {
    TemplateActivite deleteByUiIdAndTaNum(String uiId, Integer taNum);
    Optional<TemplateActivite> findByTaNum(Integer taNum);
    List<TemplateActivite> findByUiId(String uiId);
}
