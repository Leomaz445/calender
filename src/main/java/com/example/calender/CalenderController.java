package com.example.calender;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

import static com.example.calender.constants.CalenderConstants.*;
import static javafx.application.Platform.exit;

public class CalenderController {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private ChoiceBox<String> mothChoiceBox;

    @FXML
    private ChoiceBox<String> yearChoiceBox;

    @FXML
    private TextArea textField;

    private CalenderImpl calenderImpl;

    @FXML
    void exitCalender(ActionEvent event) {
        if (calenderImpl.userWantToExitTheGame())
            exit();
    }

    @FXML
    void saveMeeting(ActionEvent event) {
        if (validYearMonth() && calenderImpl.validDayOfTheMont()) {
            Optional<LocalDate> selectedDate = calenderImpl.getSelectedDate();
            selectedDate.ifPresent(localDate -> calenderImpl.addValueToHashMap(localDate, textField.getText()));
            textField.setText("");
        }
    }

    @FXML
    void goCalcDate(ActionEvent event) {
        if (validYearMonth()) {
            printCalender(calenderImpl.getYear(), calenderImpl.getMonth());
        }
    }

    public void initialize() {
        calenderImpl = new CalenderImpl();
        mothChoiceBox.setItems(MONTHS);
        yearChoiceBox.setItems(YEAR_OPTIONS);
        buildLabelDayOfTheWeek();
        gridAdditionalPropertiesSetUp();
    }

    private void printCalender(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        DayOfWeek startDay = date.getDayOfWeek();
        YearMonth yearMonth = YearMonth.of(year, month);
        int startDayValue = startDay.getValue();
        int numDays = yearMonth.lengthOfMonth();
        for (int i = 1; i <= numDays; i++) {
            String day = String.valueOf(i);
            Button dayButton = new Button(day);
            dayButton.setAlignment(Pos.CENTER);
            dayButton.setPrefSize(calendarGrid.getPrefWidth() / 7, calendarGrid.getPrefHeight() / 6);
            dayButton.getStyleClass().add("day-button");
            dayButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    calenderImpl.setDayOfTheMonth(dayButton);
                    calenderImpl.loadSchedule(textField);
                }
            });
            calendarGrid.add(dayButton, (startDayValue + i - 1) % 7, (startDayValue + i - 1) / 7 + 1);
        }
    }

    private void buildLabelDayOfTheWeek() {
        for (int i = 0; i < 7; i++) {
            Label daysOfTheWeekLabel = new Label(DAYS_OF_WEEK[i]);
            daysOfTheWeekLabel.setPrefSize(calendarGrid.getPrefWidth() / 7, calendarGrid.getPrefHeight() / 6);
            calendarGrid.add(daysOfTheWeekLabel, i, 0);
        }
    }

    private boolean validYearMonth() {
        return calenderImpl.setYear(yearChoiceBox) && calenderImpl.setMonth(mothChoiceBox);
    }

    private void gridAdditionalPropertiesSetUp() {
        calendarGrid.setPadding(new Insets(10, 10, 10, 10));
        calendarGrid.setVgap(8);
        calendarGrid.setHgap(10);
        calendarGrid.setAlignment(Pos.TOP_CENTER);
    }
}
