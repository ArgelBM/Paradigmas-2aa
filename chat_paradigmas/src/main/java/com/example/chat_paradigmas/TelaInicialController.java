package com.example.chat_paradigmas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private TextField quantidade;

    @FXML
    void iniciar(ActionEvent event) throws IOException {
    		String text = quantidade.getText();
    		int value = Integer.parseInt(text);
        ControllerDaTela.telasDeChat(value);

    }

}
