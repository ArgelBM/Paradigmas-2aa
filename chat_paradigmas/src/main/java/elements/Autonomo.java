package elements;

import java.util.List;

public class Autonomo {

    List<Mensagens> mensagens;

    public void enviarMensagem(String conteudo, boolean enviada){

        mensagens.add(new Mensagens(enviada, conteudo));

    }


}
