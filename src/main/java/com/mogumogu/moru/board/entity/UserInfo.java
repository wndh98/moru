package com.mogumogu.moru.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "USER_INFO_TB")
@Data
public class UserInfo {

    @Id
    private String uiId;
    private String uiNickname;
    private String uiDel;
    private int uiPoint;
    private LocalDateTime uiRegist;
    private String uiPassword;
    private String uiEmail;
    private Integer uiHeight;
    private Integer uiAge;
    private String uiGender;

    // Getters and Setters
}