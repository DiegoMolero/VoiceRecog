package diegoycarlos.uclm.voicerecog.JSONOBjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 20/04/2017.
 */

public class WitJSON {
    @SerializedName(value ="entities")
    private Entities entities;

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }
}
