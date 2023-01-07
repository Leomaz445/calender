package com.example.calender.constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalenderConstants {
    public static final ObservableList<String> MONTHS =
            FXCollections.observableArrayList("January", "February", "March", "April", "May"
            , "June", "July", "August", "September", "October", "November", "December");
    public static final String[] DAYS_OF_WEEK =
            {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static final ObservableList<String> YEAR_OPTIONS =
            FXCollections.observableArrayList("2020", "2021", "2022");


}
