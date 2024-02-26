package com.example.chat_paradigmas;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

import javafx.application.Platform;

class Usuario extends Thread {
    private String nome;
    private BlockingQueue<String> buffer;
    private boolean mensagemAutomatica;

    public Usuario(String nome, BlockingQueue<String> buffer, boolean mensagemAutomatica) {
        this.nome = nome;
        this.buffer = buffer;
        this.mensagemAutomatica = mensagemAutomatica;
    }


    public void run() {
        try {
            if (mensagemAutomatica) {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        String mensagem = "Ola Mundo";
                        try {
                            buffer.put(mensagem);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 0, 1000);

            } else {
                // Your manual message sending logic
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public BlockingQueue<String> getBuffer() {
			return buffer;
		}

		public void setBuffer(BlockingQueue<String> buffer) {
			this.buffer = buffer;
		}

		public boolean isMensagemAutomatica() {
			return mensagemAutomatica;
		}

		public void setMensagemAutomatica(boolean mensagemAutomatica) {
			this.mensagemAutomatica = mensagemAutomatica;
		}

    // Getters and setters
    
}
