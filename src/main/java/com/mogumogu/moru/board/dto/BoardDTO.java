package com.mogumogu.moru.board.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class BoardDTO {
    private int boNum;
    private UserInfoDTO userInfoDTO;
    private String boTitle;
    private String boContent;
    private String boWriter;
    private int boReply;
    private int boReplyDept;
    private Date boRegist;
    private int boView;
    private String boDel;

}
