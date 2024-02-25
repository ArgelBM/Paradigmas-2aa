module com.example.chat_paradigmas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chat_paradigmas to javafx.fxml;
    exports com.example.chat_paradigmas;
}