package diegoycarlos.uclm.voicerecog.Comida;

/**
 * Created by User on 20/04/2017.
 */

public class Pizza extends  Comida{
    private String tipo; //barbacoa,margarita,vegetal,bacon,cuatro quesos

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pizza(String tipo) {
        super(tipo);
    }
}
