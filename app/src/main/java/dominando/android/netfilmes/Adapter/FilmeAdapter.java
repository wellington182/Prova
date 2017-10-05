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

import dominando.android.netfilmes.Model.Filme;
import dominando.android.netfilmes.R;

/**
 * Created by Diego on 13/09/2017.
 */

public class FilmeAdapter extends ArrayAdapter<Filme> {

    private int resource;
    private List<Filme> filmes;

    public FilmeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Filme> objects) {
        super(context, resource, objects);
        this.resource = resource;
        filmes = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Filme filme = filmes.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textTitulo = (TextView) mView.findViewById(R.id.textTitulo);
        TextView textGenero = (TextView) mView.findViewById(R.id.textGenero);

        if(textID != null){
            textID.setText(String.valueOf(filme.getId()));
        }
        if(textTitulo != null){
            textTitulo.setText(filme.getTitulo());
        }
        if(textGenero != null){
            textGenero.setText(String.valueOf(filme.getGenero().getDescricao()));
        }

        return mView;
    }
}
