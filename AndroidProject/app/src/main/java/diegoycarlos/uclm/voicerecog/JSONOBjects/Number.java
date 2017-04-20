package diegoycarlos.uclm.voicerecog.JSONOBjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 20/04/2017.
 */

public class Number {
    @SerializedName("confidence")
    public String confidence;

    @SerializedName("value")
    public String value;

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("type")
    public String type;
}
