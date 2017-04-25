package diegoycarlos.uclm.voicerecog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import ai.wit.sdk.IWitListener;
import ai.wit.sdk.Wit;
import ai.wit.sdk.model.WitOutcome;
import diegoycarlos.uclm.voicerecog.Comida.Comida;
import diegoycarlos.uclm.voicerecog.Comida.Hamburguesa;
import diegoycarlos.uclm.voicerecog.Comida.Pedido;
import diegoycarlos.uclm.voicerecog.Comida.Pizza;
import diegoycarlos.uclm.voicerecog.JSONOBjects.Entities;
import diegoycarlos.uclm.voicerecog.JSONOBjects.WitJSON;

public class MainActivity extends Activity implements IWitListener {

    TextView grabar;
    String strSpeech2Text;
    Wit _wit;
    Pedido pedido;
    ListView lstpedido;
    AdaptadorListaComida adaptadorListaComida;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String accessToken = "DPWQHQRACLK3QSMCWH6L4FWNXELQPJRP";
        _wit = new Wit(accessToken,this);
        pedido = new Pedido();
        lstpedido = (ListView) findViewById(R.id.list_pedido);
        adaptadorListaComida = new AdaptadorListaComida(this,pedido,lstpedido);
        lstpedido.setAdapter(adaptadorListaComida);
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

                    ((TextView) findViewById(R.id.txtGrabarVoz)).setText(strSpeech2Text);
                    ((TextView) findViewById(R.id.txtText)).setText("Estamos preparando tu pedido :)");
                    _wit.captureTextIntent(strSpeech2Text);
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

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(arrayList);
        Log.i("JSON OUTPUT ->",jsonOutput);
        ((TextView) findViewById(R.id.txtText)).setText("Done!");
        WitJSON[] response = FromJSON(jsonOutput);
        Comida comida =analizeData(response);
        if(comida!= null){
            pedido.addComida(comida);
            updateList();
        }

    }

    @Override
    public void witDidStartListening() {
        ((TextView) findViewById(R.id.txtText)).setText("Witting...");
    }

    @Override
    public void witDidStopListening() {
        ((TextView) findViewById(R.id.txtText)).setText("Processing...");
    }

    @Override
    public void witActivityDetectorStarted() {
        ((TextView) findViewById(R.id.txtText)).setText("Listening");
    }

    @Override
    public String witGenerateMessageId() {
        return null;
    }

    public WitJSON[] FromJSON(String data){
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return  gson.fromJson(data, WitJSON[].class);
    }

    public Comida analizeData(WitJSON[] data){
        Entities aux = data[0].getEntities();
        String strcomida= null;
        int amount=1;
        Comida comida=null;
        if(aux.getNumber().isEmpty() == false){
            amount = Integer.parseInt(aux.getNumber().get(0).getValue());
        }
        if(aux.getIntentTipoComida().isEmpty()==false ){
            strcomida=aux.getIntentTipoComida().get(0).getValue();
            if(strcomida.equals("hamburguesa") && aux.getIntentTipoHamburguesa().isEmpty() == false){
                comida = new Hamburguesa(aux.getIntentTipoHamburguesa().get(0).getValue(),amount);
            }
            if(strcomida.equals("pizza") && aux.getIntentTipoPizza().isEmpty() == false){
                comida = new Pizza(aux.getIntentTipoPizza().get(0).getValue(),amount);
                Log.i("INFO TIPO",aux.getIntentTipoPizza().get(0).getValue());
            }
            if(strcomida.equals("hamburguesa") && aux.getIntentTipoHamburguesa().isEmpty() == true){
                Toast.makeText(getBaseContext(), "Repita su pedido con el tipo de hamburguesa correspondiente" , Toast.LENGTH_LONG ).show();
            }
            if(strcomida.equals("pizza") && aux.getIntentTipoPizza().isEmpty() == true){
                Toast.makeText(getBaseContext(), "Repita su pedido con el tipo de pizza correspondiente" , Toast.LENGTH_LONG ).show();
            }
        }else{
            Toast.makeText(getBaseContext(), "Haga su pedido correctamente" , Toast.LENGTH_LONG ).show();
        }
        return comida;
    }
    public void updateList(){
        ((BaseAdapter) lstpedido.getAdapter()).notifyDataSetChanged();
    }
    public void showMenu(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Hamburguesa\n\n Tipos:\n\t-Ternera.\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                "3,5€\n\t-Pollo.\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                "4€\n\nPizzas\n\n Tipos:"+
                "\n\t-Barbacoa.\t\t\t\t\t\t\t\t\t\t\t\t" +
                "7,5€\n\t-Cuatro Quesos.\t\t\t\t\t\t\t\t" +
                "7€\n\t-Margarita.\t\t\t\t\t\t\t\t\t\t\t\t" +
                "8€\n\t-Bacon.\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                "6,5€\n\t-Vegetal.\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                "9€")
                .setTitle("Menu")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void showInfo(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Creadores:\n\n" +
                "\t\t\tCarlos Sobrino Pérez\n" +
                "\t\t\tCarlos.Sobrino1@alu.uclm.es\n\n" +
                "\t\t\tDiego Molero Marín\n" +
                "\t\t\tDiego.Molero@alu.uclm.es\n\n")
                .setTitle("Información")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
