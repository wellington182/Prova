package dominando.android.netfilmes.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import dominando.android.netfilmes.Model.Genero;
import dominando.android.netfilmes.R;

/**
 * Created by Wellington on 05/10/2017.
 */

public class GeneroAdapter extends ArrayAdapter<Genero> {

    private int resource;
    private List<Genero> generos;

    public GeneroAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Genero> objects) {
        super(context, resource, objects);
        this.resource = resource;
        generos = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Genero genero = generos.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textDescricao = (TextView) mView.findViewById(R.id.textDescricao);

        if(textID != null){
            textID.setText(String.valueOf(genero.getId()));
        }
        if(textDescricao != null){
            textDescricao.setText(genero.getDescricao());
        }

        return mView;
    }
}
