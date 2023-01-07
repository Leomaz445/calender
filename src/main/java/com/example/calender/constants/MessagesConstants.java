package com.example.calender.constants;

import com.example.calender.Messages;

public class MessagesConstants {

    public static final String CALENDER_APPLICATION = "Calender Application";
    public static final String YOUR_MESSAGE_SAVED = "Your Message Saved";
    public static final String MESSAGE_SAVED = "Message Saved";
    public static final String PICK_A_DATE = "Pick a date";
    public static final String PICK_MONTH_AND_YEAR = "Pick month and year";
    public static final String PICK_A_DAY = "Pick a day";
    public static final String PICK_A_MONTH = "Pick a month";
    public static final String YOU_ARE_ABOUT_TO_EXIT = "You are about to exit";
    public static final String ARE_YOU_SURE = "Are you sure?";
    public static final Messages SAVED_MESSAGE = new Messages.MessagesBuilder()
            .setTitle(CALENDER_APPLICATION)
            .setHeader(YOUR_MESSAGE_SAVED)
            .setContent(MESSAGE_SAVED).build();

    public static final Messages CANT_SAVE_OR_LOAD_CHOOSE_DATE_MESSAGE = new Messages.MessagesBuilder()
            .setTitle(CALENDER_APPLICATION)
            .setHeader(PICK_A_DATE)
            .setContent(PICK_MONTH_AND_YEAR).build();

    public static final Messages CANT_SAVE_OR_LOAD_CHOOSE_DAY_MESSAGE = new Messages.MessagesBuilder()
            .setTitle(CALENDER_APPLICATION)
            .setHeader(PICK_A_DAY)
            .setContent(PICK_A_DAY).build();

    public static final Messages PICK_A_MONTH_MESSAGE = new Messages.MessagesBuilder()
            .setTitle(CALENDER_APPLICATION)
            .setHeader(PICK_A_MONTH)
            .setContent(PICK_A_MONTH).build();

    public static final Messages EXIT_APP_MESSAGE = new Messages.MessagesBuilder()
            .setTitle(CALENDER_APPLICATION)
            .setHeader(YOU_ARE_ABOUT_TO_EXIT)
            .setContent(ARE_YOU_SURE).build();

}
