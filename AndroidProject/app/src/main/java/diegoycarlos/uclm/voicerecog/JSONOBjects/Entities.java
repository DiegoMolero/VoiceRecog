package diegoycarlos.uclm.voicerecog.JSONOBjects;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20/04/2017.
 */

public class Entities {
    @SerializedName(value ="number")
    private List<Number> number = new ArrayList<Number>();

    @SerializedName(value ="intentTipoComida")
    private List<IntentTipoComida> intentTipoComida = new ArrayList<IntentTipoComida>();

    @SerializedName(value ="intentTipoHamburguesa")
    private List<IntentTipoHamburguesa> intentTipoHamburguesa = new ArrayList<IntentTipoHamburguesa>();

    @SerializedName(value ="intentTipoPizza")
    private List<IntentTipoPizza> intentTipoPizza = new ArrayList<IntentTipoPizza>();

    public List<IntentTipoComida> getIntentTipoComida() {
        return intentTipoComida;
    }

    public void setIntentTipoComida(List<IntentTipoComida> intentTipoComida) {
        this.intentTipoComida = intentTipoComida;
    }

    public List<IntentTipoHamburguesa> getIntentTipoHamburguesa() {
        return intentTipoHamburguesa;
    }

    public void setIntentTipoHamburguesa(List<IntentTipoHamburguesa> intentTipoHamburguesa) {
        this.intentTipoHamburguesa = intentTipoHamburguesa;
    }

    public List<IntentTipoPizza> getIntentTipoPizza() {
        return intentTipoPizza;
    }

    public void setIntentTipoPizza(List<IntentTipoPizza> intentTipoPizza) {
        this.intentTipoPizza = intentTipoPizza;
    }





    public List<Number> getNumber() {
        return number;
    }

    public void setNumber(List<Number> number) {
        this.number = number;
    }
}
