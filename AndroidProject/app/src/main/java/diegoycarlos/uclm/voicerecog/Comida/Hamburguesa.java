package diegoycarlos.uclm.voicerecog.Comida;

/**
 * Created by User on 20/04/2017.
 */

public class Hamburguesa extends Comida{
    private String tipo; //pollo y ternera(carne o vacuno)

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Hamburguesa(String tipo) {
        super(tipo);
    }
}
