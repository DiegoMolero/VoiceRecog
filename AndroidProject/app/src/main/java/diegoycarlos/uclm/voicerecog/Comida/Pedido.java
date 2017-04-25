package diegoycarlos.uclm.voicerecog.Comida;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by User on 20/04/2017.
 */

public class Pedido {
    private ArrayList<Comida> lista;

    public Pedido() {
        lista = new ArrayList<Comida>();
    }

    public ArrayList<Comida>getLista() {
        return lista;
    }
    public void setLista(ArrayList<Comida> lista) {
        this.lista = lista;
    }
    public void addComida(Comida a){
        lista.add(a);
    }
    public Comida getComida(int i){
        return lista.get(i);
    }
    public int getSize(){
        return lista.size();
    }
    public void removeComida(int position){
        lista.remove(position);
    }
}
