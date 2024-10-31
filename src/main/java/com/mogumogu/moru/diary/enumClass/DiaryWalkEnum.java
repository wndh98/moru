package com.mogumogu.moru.diary.enumClass;

public enum DiaryWalkEnum {
    Y('Y'),N('N');
    private final char isBool;
    private DiaryWalkEnum(char bool){
        this.isBool=bool;
    }
    public char getIsBool(){
        return isBool;
    }
}
