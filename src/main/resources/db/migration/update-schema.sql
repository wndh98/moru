ALTER TABLE board_base_tb
    DROP
        FOREIGN KEY BOARD_BASE_UI_ID_FK;

ALTER TABLE board_file_tb
    DROP
        FOREIGN KEY BOARD_FILE_UI_ID_FK;

ALTER TABLE comment_base_tb
    DROP
        FOREIGN KEY COMMENT_BASE_BO_NUM_FK;

ALTER TABLE comment_base_tb
    DROP
        FOREIGN KEY COMMENT_BASE_UI_ID_FK;

ALTER TABLE diary_hashtag_link_tb
    DROP
        FOREIGN KEY DIARY_HASHTAG_LINK_DH_NUM_FK;

ALTER TABLE diary_hashtag_link_tb
    DROP
        FOREIGN KEY DIARY_HASHTAG_LINK_DW_NUM_FK;

ALTER TABLE diary_like_tb
    DROP
        FOREIGN KEY DIARY_LIKE_DW_NUM_FK;

ALTER TABLE diary_like_tb
    DROP
        FOREIGN KEY DIARY_LIKE_UI_ID_FK;

ALTER TABLE diary_walk_file_tb
    DROP
        FOREIGN KEY DIARY_WALK_FILE_DW_NUM_FK;

ALTER TABLE diary_walk_file_tb
    DROP
        FOREIGN KEY DIARY_WALK_FILE_UI_ID_FK;

ALTER TABLE diary_walk_tb
    DROP
        FOREIGN KEY DIARY_WALK_UI_ID_FK;

ALTER TABLE pet_info_tb
    DROP
        FOREIGN KEY PET_INFO_PB_NUM_FK;

ALTER TABLE pet_info_tb
    DROP
        FOREIGN KEY PET_INFO_UI_ID_FK;

ALTER TABLE pet_weight_tb
    DROP
        FOREIGN KEY PET_WEIGHT_PI_NUM_FK;

ALTER TABLE template_activite_list_tb
    DROP
        FOREIGN KEY TEMPLATE_ACTIVITE_LIST_TA_NUM_FK;

ALTER TABLE template_activite_list_tb
    DROP
        FOREIGN KEY TEMPLATE_ACTIVITE_LIST_UA_NUM_FK;

ALTER TABLE template_food_list_tb
    DROP
        FOREIGN KEY TEMPLATE_FOOD_LIST_TF_NUM_FK;

ALTER TABLE user_activite_calendar_tb
    DROP
        FOREIGN KEY USER_ACTIVITE_CALENDAR_UA_NUM_FK;

ALTER TABLE user_weight_tb
    DROP
        FOREIGN KEY USER_WEIGHT_UI_ID_FK;

DROP TABLE board_base_tb;

DROP TABLE board_file_tb;

DROP TABLE comment_base_tb;

DROP TABLE diary_hashtag_link_tb;

DROP TABLE diary_hashtag_tb;

DROP TABLE diary_like_tb;

DROP TABLE diary_walk_file_tb;

DROP TABLE diary_walk_tb;

DROP TABLE pet_breed_tb;

DROP TABLE pet_info_tb;

DROP TABLE pet_weight_tb;

DROP TABLE template_activite_list_tb;

DROP TABLE template_activite_tb;

DROP TABLE template_food_list_tb;

DROP TABLE template_food_tb;

DROP TABLE user_activite_calendar_tb;

DROP TABLE user_activite_tb;

DROP TABLE user_email_code_tb;

DROP TABLE user_food_calendar_tb;

ALTER TABLE user_refresh_token_tb
    DROP
        COLUMN UI_EMAIL;

ALTER TABLE user_refresh_token_tb
    DROP
        COLUMN URT_KEEP_LOGIN;

ALTER TABLE user_refresh_token_tb
    MODIFY expiration VARCHAR (255);

ALTER TABLE user_refresh_token_tb
    MODIFY expiration VARCHAR (255) NULL;

ALTER TABLE user_info_tb
    MODIFY ui_gender VARCHAR (255);

ALTER TABLE user_refresh_token_tb
    MODIFY ui_id VARCHAR (255) NULL;

ALTER TABLE user_weight_tb
    MODIFY ui_id VARCHAR (255) NULL;

ALTER TABLE user_info_tb
    MODIFY ui_nickname VARCHAR (255);

ALTER TABLE user_info_tb
    MODIFY ui_nickname VARCHAR (255) NULL;

ALTER TABLE user_refresh_token_tb
    MODIFY ui_nickname VARCHAR (255);

ALTER TABLE user_refresh_token_tb
    MODIFY ui_nickname VARCHAR (255) NULL;

ALTER TABLE user_info_tb
    MODIFY ui_password VARCHAR (255);

ALTER TABLE user_info_tb
    MODIFY ui_regist datetime NULL;

ALTER TABLE user_info_tb
    MODIFY ui_role VARCHAR (255);

ALTER TABLE user_refresh_token_tb
    MODIFY urt_token VARCHAR (255);

ALTER TABLE user_weight_tb
    DROP
        COLUMN uw_date;

ALTER TABLE user_weight_tb
    ADD uw_date datetime NULL;

ALTER TABLE user_weight_tb
    MODIFY uw_date datetime NULL;