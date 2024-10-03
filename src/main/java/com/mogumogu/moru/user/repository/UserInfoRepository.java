package com.mogumogu.moru.user.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserInfoRepository {
   private final EntityManager em;


}
