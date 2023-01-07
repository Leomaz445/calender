package com.example.calender.alert;


import com.example.calender.Messages;
import com.example.calender.enums.ConfirmationCode;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.example.calender.constants.MessagesConstants.EXIT_APP_MESSAGE;


public class ConfirmationMessagesAlert {

    private static final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
    private final Map<ConfirmationCode, Supplier<Boolean>> mapOfConfirmation = new HashMap<ConfirmationCode, Supplier<Boolean>>() {{
        put(ConfirmationCode.DO_YOU_WANT_TO_SAVE, () -> createAlert(EXIT_APP_MESSAGE));
    }};


    public boolean getAlert(ConfirmationCode confirmationCode) {
        return mapOfConfirmation.get(confirmationCode).get();
    }

    private boolean createAlert(Messages messages) {
        alertConfirmation.setTitle(messages.getTitle());
        alertConfirmation.setHeaderText(messages.getHeader());
        alertConfirmation.setContentText(messages.getContent());
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        return option.isPresent() && option.get() == ButtonType.YES;
    }


}
