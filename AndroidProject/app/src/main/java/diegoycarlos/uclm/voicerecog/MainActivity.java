package diegoycarlos.uclm.voicerecog;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import ai.wit.sdk.IWitListener;
import ai.wit.sdk.Wit;
import ai.wit.sdk.model.WitOutcome;

public class MainActivity extends Activity implements IWitListener {

    TextView grabar;
    String strSpeech2Text;
    Wit _wit;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String accessToken = "YOUR CLIENT ACCESS TOKEN HERE";
        _wit = new Wit(accessToken, this);
        _wit.enableContextLocation(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.
                                    EXTRA_RESULTS);
                    strSpeech2Text = speech.get(0);

                    grabar.setText(strSpeech2Text);
                }

                break;
            default:

                break;
        }
    }

    public void onClickImgBtnHablar(View v) {

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-ES");
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void witDidGraspIntent(ArrayList<WitOutcome> arrayList, String s, Error error) {

    }

    @Override
    public void witDidStartListening() {

    }

    @Override
    public void witDidStopListening() {

    }

    @Override
    public void witActivityDetectorStarted() {

    }

    @Override
    public String witGenerateMessageId() {
        return null;
    }
}
