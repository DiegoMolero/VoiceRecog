package diegoycarlos.uclm.voicerecog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import diegoycarlos.uclm.voicerecog.Comida.Comida;
import diegoycarlos.uclm.voicerecog.Comida.Pedido;

/**
 * Created by User on 25/04/2017.
 */


public class AdaptadorListaComida extends ArrayAdapter<Comida> {
    private Activity context;
    private ArrayList<Comida> comidas;
    private ImageButton boton;
    private Pedido pedido;
    private ListView listComida;
    public AdaptadorListaComida(Activity context, Pedido pedido,ListView listComida) {
        super(context, R.layout.layout_item_pedido, pedido.getLista());
        this.context = context;
        this.comidas = pedido.getLista();
        this.pedido = pedido;
        this.listComida=listComida;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_item_pedido, null);
        boton = (ImageButton) v.findViewById(R.id.imageButton);
        TextView lblComida = (TextView) v.findViewById(R.id.lblComida);
        lblComida.setText(comidas.get(position).getClass().getSimpleName());
        TextView lblTipo = (TextView) v.findViewById(R.id.lblTipo);
        lblTipo.setText(comidas.get(position).getTipo());
        TextView lblAmount = (TextView) v.findViewById(R.id.lblAmount);
        lblAmount.setText(Integer.toString(comidas.get(position).getAmount()));
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.removeComida(position);
                ((BaseAdapter) listComida.getAdapter()).notifyDataSetChanged();
            }
        });
        return v;
    }
}
