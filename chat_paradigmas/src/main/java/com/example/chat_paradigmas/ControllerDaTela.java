package com.example.chat_paradigmas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;

public class ControllerDaTela {


    public class TesteController implements Initializable{

        @FXML
        private Label label;

        @FXML
        private Label label2;

        @FXML
        private ScrollBar scroll;

        @FXML
        private ListView<String> list;

        @FXML
        private CheckBox check;

        @FXML
        private TextField text;

        @FXML
        private Button button;

//	#FXML
//	private User user;

        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {
            //label.setText(user.getName());
            //server.getAll();
        }

        @FXML
        public void reload() {
            //label.setText(user.getName());
            //list.setItems(server.getAll());
        }

        @FXML
        public void enviar(ActionEvent event)
        {
            if(!text.getText().isEmpty())
            {
                //server.add(text.getText());
                reload();
            }
        }

        @FXML
        public void autonomo(ActionEvent event)
        {
            //user.setAutonomo();
        }

    }
}
