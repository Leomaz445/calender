package com.example.calender.enums;

public enum InformationCode {
    SAVED(1),
    CANT_SAVE_OR_LOAD_CHOOSE_DATE(2),
    CANT_SAVE_OR_LOAD_CHOOSE_DAY(3),
    PICK_A_MONTH(4);

    private final int value;

    InformationCode(int value) {
        this.value = value;
    }
}
