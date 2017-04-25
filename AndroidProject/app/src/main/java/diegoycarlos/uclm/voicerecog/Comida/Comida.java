package diegoycarlos.uclm.voicerecog.Comida;

/**
 * Created by User on 20/04/2017.
 */

public class Comida {
    protected String tipo;
    protected int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Comida(String tipo, int amount) {
        this.amount = amount;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
