package com.mogumogu.moru.template.repository;

import com.mogumogu.moru.template.entity.TemplateFood;
import com.mogumogu.moru.template.projection.TemplateFoodProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateFoodRepository extends JpaRepository<TemplateFood, Integer> {
    TemplateFood deleteByUiIdAndTfNum(String uiId, Integer tfNum);
    List<TemplateFoodProjection> findByUiId(String uiId);

    Optional<TemplateFood> findByTfNum(Integer tfNum);
}
