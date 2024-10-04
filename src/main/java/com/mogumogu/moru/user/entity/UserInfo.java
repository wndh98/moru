package com.mogumogu.moru.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="USER_INFO_TB")
public class UserInfo {
    @Id
    @Column(name="UI_ID")
    private String uiId;
}
