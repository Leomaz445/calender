package com.example.calender;

import com.example.calender.alert.ConfirmationMessagesAlert;
import com.example.calender.alert.InformationMessagesAlert;
import com.example.calender.enums.ConfirmationCode;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Optional;

import static com.example.calender.constants.MessagesConstants.*;
import static com.example.calender.enums.InformationCode.PICK_A_MONTH;
import static com.example.calender.enums.InformationCode.*;

public class CalenderImpl {
    private int dayOfTheMonth;
    private int month;
    private int year;
    private final HashMap<LocalDate, String> data;
    private final InformationMessagesAlert informationMessagesAlert;
    private final ConfirmationMessagesAlert confirmationMessagesAlert;

    public CalenderImpl() {
        this.dayOfTheMonth = 0;
        this.month = 0;
        this.year = 0;
        this.data = new HashMap<>();
        this.informationMessagesAlert = new InformationMessagesAlert();
        this.confirmationMessagesAlert = new ConfirmationMessagesAlert();
    }

    public boolean validDayOfTheMont() {
        YearMonth yearMonth = YearMonth.of(year, month);
        int numDays = yearMonth.lengthOfMonth();
        if (numDays >= this.dayOfTheMonth && this.dayOfTheMonth != 0) {
            return true;
        }
        informationMessagesAlert.getAlert(CANT_SAVE_OR_LOAD_CHOOSE_DAY, CANT_SAVE_OR_LOAD_CHOOSE_DAY_MESSAGE);
        return false;
    }

    public void setDayOfTheMonth(Button dayButton) {
        try {
            this.dayOfTheMonth = Integer.parseInt(dayButton.getText());
        } catch (NumberFormatException e) {
            cantSaveOrLoadDateError();
        }
    }

    public int getMonth() {
        return month;
    }

    public boolean setMonth(ChoiceBox<String> mothChoiceBox) {
        try {
            Optional<String> month = Optional.ofNullable(mothChoiceBox.getValue());
            if (month.isPresent()) {
                this.month = mothChoiceBox.getItems().indexOf(month.get()) + 1;
                return true;
            }
        } catch (ClassCastException | NullPointerException | DateTimeException e) {
            informationMessagesAlert.getAlert(PICK_A_MONTH, PICK_A_MONTH_MESSAGE);
            return false;
        }
        informationMessagesAlert.getAlert(PICK_A_MONTH, PICK_A_MONTH_MESSAGE);
        return false;
    }

    public int getYear() {
        return year;
    }

    public boolean setYear(ChoiceBox<String> yearChoiceBox) {
        try {
            this.year = Integer.parseInt(yearChoiceBox.getValue());
            return true;
        } catch (NumberFormatException e) {
            cantSaveOrLoadDateError();
            return false;
        }
    }

    public void addValueToHashMap(LocalDate selectedDate, String message) {
        data.put(selectedDate, message);
        informationMessagesAlert.getAlert(SAVED, SAVED_MESSAGE);
    }

    public Optional<LocalDate> getSelectedDate() {
        try {
            return Optional.of(LocalDate.of(this.year, this.month, this.dayOfTheMonth));
        } catch (DateTimeException e) {
            cantSaveOrLoadDateError();
            return Optional.empty();
        }
    }

    public void loadSchedule(TextArea textField) {
        LocalDate date = LocalDate.of(this.year, this.month, this.dayOfTheMonth);
        textField.setText(data.getOrDefault(date, ""));
    }

    public boolean userWantToExitTheGame() {
        return confirmationMessagesAlert.getAlert(ConfirmationCode.DO_YOU_WANT_TO_SAVE);
    }

    private void cantSaveOrLoadDateError() {
        informationMessagesAlert.getAlert(CANT_SAVE_OR_LOAD_CHOOSE_DATE,
                CANT_SAVE_OR_LOAD_CHOOSE_DATE_MESSAGE);
    }
}
