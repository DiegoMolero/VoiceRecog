package diegoycarlos.uclm.voicerecog.Comida;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 20/04/2017.
 */

public class Pedido {
    private List<Comida> lista;

    public Pedido() {
        lista = new ArrayList<Comida>();
    }

    public List<Comida> getLista() {
        return lista;

    }

    public void setLista(List<Comida> lista) {
        this.lista = lista;
    }
}
