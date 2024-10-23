package com.mogumogu.moru.template.repository;

import com.mogumogu.moru.template.entity.UserActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActiviteRepository extends JpaRepository<UserActivite, Integer> {

}
