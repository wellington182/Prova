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

import dominando.android.netfilmes.Adapter.GeneroAdapter;
import dominando.android.netfilmes.DAO.GeneroDAO;
import dominando.android.netfilmes.Model.Genero;
import dominando.android.netfilmes.R;

public class ListarGenerosActivity extends AppCompatActivity {

    private ListView listaGeneros;
    private GeneroAdapter myAdapter;
    GeneroDAO generoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_generos);

        carregarElementos();

        listaGeneros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Genero genero = (Genero) parent.getItemAtPosition(position);
                createDialog(genero);
            }
        });
    }

    public void createDialog(final Genero genero){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setItems(R.array.options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Intent it = new Intent(ListarGenerosActivity.this, AtualizarGeneroActivity.class);
                        it.putExtra("genero", genero);
                        startActivity(it);
                        break;
                    case 1:
                        generoDAO.deletaRegistro(genero.getId());
                        Toast.makeText(ListarGenerosActivity.this, "Gênero removido com sucesso.", Toast.LENGTH_SHORT).show();
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
        listaGeneros = (ListView) findViewById(R.id.listGeneros);
        generoDAO = new GeneroDAO(this);
        List<Genero> generos = generoDAO.carregaDadosLista();
        myAdapter = new GeneroAdapter(this,R.layout.activity_item_genero,generos);
        listaGeneros.setAdapter(myAdapter);
    }
}
