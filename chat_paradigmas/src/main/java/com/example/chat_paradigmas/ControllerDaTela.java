package com.example.chat_paradigmas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ControllerDaTela implements Initializable {

	@FXML
	private static Label label;

	@FXML
	private Label label2;

	@FXML
	private ScrollBar scroll;

	@FXML
	private ListView<String> list = new ListView<String>();

	@FXML
	private CheckBox check;

	@FXML
	private TextArea text;

	@FXML
	private Button button;

	private static ArrayList<String> lista = new ArrayList<String>();
	private static Semaphore semaforo = new Semaphore(1);

	private static BlockingQueue<String> buffer = new LinkedBlockingQueue<>();

	@FXML
	private static Servidor servidor = new Servidor(buffer, lista, semaforo);

	@FXML
	private Usuario usuario = new Usuario("", buffer, false);

	private static ControllerDaTela controller;
	private static Object controler;
	private static Object subControler;
	private static Stage stage = Main.getStage();

	public static void iniciar() throws IOException {
		stage.close();
		FXMLLoader loader = new FXMLLoader(ControllerDaTela.class.getResource("telaInicial.fxml"));
		Parent root = loader.load();
		controler = loader.getController();
		Scene mainScene = new Scene(root, 700, 500);
		stage.setTitle("Rede social (:");
		stage.setScene(mainScene);
		stage.show();
	}

	public static void setControler(Object controler) {
		ControllerDaTela.controler = controler;
	}

	public static Object getControler() {
		return controler;
	}

	public static void fechar() {
		stage.close();
	}

	public static void telaPrincipal() throws IOException {

		stage.close();
		FXMLLoader loader = new FXMLLoader(ControllerDaTela.class.getResource("/gui/fxml/TelaPrincipal.fxml"));
		Parent root = loader.load();
		controler = loader.getController();
		Scene mainScene = new Scene(root, 1000, 664);
		stage.setTitle("Rede social (:");
		stage.setScene(mainScene);
		stage.show();

	}

	public static void telasDeChat(int quantidade) throws IOException {
    AtomicInteger threadCounter = new AtomicInteger(0); // Contador de threads
    for (int i = 0; i < quantidade; i++) {
        new Thread(() -> {
            try {
                int threadId = threadCounter.incrementAndGet(); // Incrementa o contador de threads
                Usuario usuario = new Usuario("Usuário " + threadId, buffer, false);
                // Armazene o usuário criado em alguma estrutura de dados, se necessário
                Platform.runLater(() -> { // Exibe a janela de chat na thread da interface do usuário
                    try {
                        usuario.start();
                        Stage chatStage = new Stage(); // Cria um novo Stage para cada janela de chat
                        FXMLLoader loader = new FXMLLoader(ControllerDaTela.class.getResource("telaDoChat.fxml"));
                        Parent root = loader.load();
                        controler = loader.getController();
                        Scene mainScene = new Scene(root, 1000, 664);
                        chatStage.setTitle("Rede social (: " + "Thread: " + threadId);
                        chatStage.setScene(mainScene);
                        chatStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
								reload();
						});
        }
    }, 0, 1000);
	}

	@FXML
	public void reload() {
		if(usuario.isMensagemAutomatica())
		{
			usuario.run();
			servidor.getLista().add(usuario.getBuffer().peek());
		}
		ObservableList<String> observableList = FXCollections.observableArrayList(servidor.getLista());
		list.setItems(observableList);
		list.refresh();
	}

	@FXML
	public void enviar(ActionEvent event) throws Exception {
			if (!text.getText().isEmpty()) {
				servidor.getLista().add(text.getText());
				System.out.println(usuario.getNome() + text.getText());
				reload();
			}
	}

	@FXML
	public void autonomo(ActionEvent event) {
		boolean autonomo = check.isSelected();
		usuario.setMensagemAutomatica(autonomo);
	}
}
