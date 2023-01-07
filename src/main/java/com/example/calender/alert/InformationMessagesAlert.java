package com.example.calender.alert;


import com.example.calender.Messages;
import com.example.calender.enums.InformationCode;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.function.Consumer;


public class InformationMessagesAlert {

    private final static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private final HashMap<InformationCode, Consumer<Messages>> mapOfInformation = new HashMap<InformationCode, Consumer<Messages>>() {{
        put(InformationCode.SAVED, msg -> createAlert(msg));
        put(InformationCode.CANT_SAVE_OR_LOAD_CHOOSE_DATE, msg -> createAlert(msg));
        put(InformationCode.CANT_SAVE_OR_LOAD_CHOOSE_DAY, msg -> createAlert(msg));
        put(InformationCode.PICK_A_MONTH, msg -> createAlert(msg));
    }};

    public void getAlert(InformationCode alertCode, Messages messages) {
        mapOfInformation.get(alertCode).accept(messages);
    }


    private void createAlert(Messages messages) {
        alertInformation.setTitle(messages.getTitle());
        alertInformation.setHeaderText(messages.getHeader());
        alertInformation.setContentText(messages.getContent());
        alertInformation.showAndWait();
    }
}
