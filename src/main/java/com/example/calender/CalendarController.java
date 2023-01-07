package com.example.calender;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.calender.constants.CalenderConstants.*;
import static javafx.application.Platform.exit;

public class CalendarController {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private ChoiceBox<String> mothChoiceBox;

    @FXML
    private ChoiceBox<String> yearChoiceBox;

    @FXML
    private TextArea textField;

    private CalendarImpl calendarImpl;

    @FXML
    void exitCalender(ActionEvent event) {
        if (calendarImpl.userWantToExitTheGame())
            exit();
    }

    @FXML
    void saveMeeting(ActionEvent event) {
        if (validYearMonth() && calendarImpl.validDayOfTheMont()) {
            Optional<LocalDate> selectedDate = calendarImpl.getSelectedDate();
            selectedDate.ifPresent(localDate -> calendarImpl.addValueToHashMap(localDate, textField.getText()));
            textField.setText("");
            changeTheStyleOfTheButtonAfterSave();
            calendarImpl.initDayOfTheMonth();
        }
    }

    private void changeTheStyleOfTheButtonAfterSave() {
        try {
            Iterator<Node> iterator = calendarGrid.getChildren().iterator();
            while (iterator.hasNext()) {
                Node child = iterator.next();
                if (child instanceof Button) {
                    String text = ((Button) child).getText();
                    if (text.equals(Integer.toString(calendarImpl.getDayOfTheMonth()))) {
                        child.setStyle(FX_TEXT_DECORATION_UNDERLINE_FX_TEXT_FILL_GREEN_FX_FONT_WEIGHT_BOLD);
                    }
                }
            }
        } catch (NoSuchElementException | NullPointerException | ClassCastException e) {
            System.out.println("error changing the color of the button - it will continue without changing");
        }
    }

    @FXML
    void goCalcDate(ActionEvent event) {
        if (validYearMonth()) {
            removeOldButtons();
            printCalender(calendarImpl.getYear(), calendarImpl.getMonth());
        }
    }

    private void removeOldButtons() {
        Iterator<Node> iterator = calendarGrid.getChildren().iterator();
        while (iterator.hasNext()) {
            Node child = iterator.next();
            if (child instanceof Button) {
                iterator.remove();
            }
        }
    }

    public void initialize() {
        calendarImpl = new CalendarImpl();
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
            dayButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    calendarImpl.setDayOfTheMonth(dayButton);
                    calendarImpl.loadSchedule(textField);
                }
            });
            if (calendarImpl.haveSavedSomething(LocalDate.of(year, month, i))) {
                dayButton.setStyle(FX_TEXT_DECORATION_UNDERLINE_FX_TEXT_FILL_GREEN_FX_FONT_WEIGHT_BOLD);
            }
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
        return calendarImpl.setYear(yearChoiceBox) && calendarImpl.setMonth(mothChoiceBox);
    }

    private void gridAdditionalPropertiesSetUp() {
        calendarGrid.setPadding(new Insets(10, 10, 10, 10));
        calendarGrid.setVgap(8);
        calendarGrid.setHgap(10);
        calendarGrid.setAlignment(Pos.TOP_CENTER);
    }
}
