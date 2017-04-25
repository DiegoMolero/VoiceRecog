package diegoycarlos.uclm.voicerecog.Comida;

/**
 * Created by User on 20/04/2017.
 */

public class Hamburguesa extends Comida{

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Hamburguesa(String tipo,int amount) {
        super(tipo,amount);
    }
}
