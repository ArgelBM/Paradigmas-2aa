package com.example.chat_paradigmas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

class Servidor {
    private BlockingQueue<String> buffer;
    private ArrayList<String> lista;
    private Semaphore semaforo;

    public Servidor(BlockingQueue<String> buffer, ArrayList<String> lista, Semaphore semaforo) {
        this.buffer = buffer;
        this.lista = lista;
        this.semaforo = semaforo;
    }

    public void iniciarServidor() {
        while (true) {
            try {
                String mensagemEnviada = buffer.take(); // Remove a próxima mensagem do buffer
                System.out.println("Servidor recebeu: " + mensagemEnviada);
                
                semaforo.acquire(); // Adquire o semáforo antes de modificar a lista
                lista.add(mensagemEnviada);
                semaforo.release(); // Libera o semáforo após modificar a lista
                
                System.out.println("\nLista "+ lista.toString() + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<String> getLista()
    {
    	return this.lista;
    }
}