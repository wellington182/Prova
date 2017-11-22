package dominando.android.netfilmes.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dominando.android.netfilmes.Adapter.FilmeAdapter;
import dominando.android.netfilmes.DAO.FilmeDAO;
import dominando.android.netfilmes.Model.Filme;
import dominando.android.netfilmes.R;

public class ListarFilmesActivity extends AppCompatActivity {

    private ListView listaFilmes;
    private FilmeAdapter myAdapter;
    FilmeDAO filmeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_filmes);

        carregarElementos();

        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) parent.getItemAtPosition(position);
                createDialog(filme);
            }
        });
    }

    public void createDialog(final Filme filme){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setItems(R.array.options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Intent it = new Intent(ListarFilmesActivity.this, AtualizarFilmeActivity.class);
                        it.putExtra("filme", filme);
                        startActivity(it);
                        break;
                    case 1:
                        filmeDAO.deletaRegistro(filme.getId());
                        Toast.makeText(ListarFilmesActivity.this, "Filme removido com sucesso.", Toast.LENGTH_SHORT).show();
                        carregarElementos();
                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void carregarElementos(){
        listaFilmes = (ListView) findViewById(R.id.listFilmes);
        filmeDAO = new FilmeDAO(this);
        List<Filme> filmes = filmeDAO.carregaDadosLista(FilmeDAO.TODOS);
        myAdapter = new FilmeAdapter(this,R.layout.activity_item_filme,filmes);
        listaFilmes.setAdapter(myAdapter);
    }
}

