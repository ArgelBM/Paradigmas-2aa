package elements;

import java.util.ArrayList;
import java.util.List;

public class Mensagens {

    public Mensagens(boolean eviada, String conteudo) {
        this.eviada = eviada;
        this.conteudo = conteudo;
    }

    public boolean eviada;
    public String conteudo;

    public boolean getEviada() {
        return eviada;
    }

    public String getConteudo() {
        return conteudo;
    }



}
