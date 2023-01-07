module com.example.calender {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calender to javafx.fxml;
    exports com.example.calender;
}