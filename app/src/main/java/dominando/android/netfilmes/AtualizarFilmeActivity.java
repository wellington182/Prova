package dominando.android.netfilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import dominando.android.netfilmes.DAO.FilmeDAO;
import dominando.android.netfilmes.DAO.GeneroDAO;
import dominando.android.netfilmes.Model.Filme;
import dominando.android.netfilmes.Model.Genero;

public class AtualizarFilmeActivity extends AppCompatActivity {

    EditText editTitulo;
    Spinner spinnerGenero;
    List<Genero> generos;
    Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_filme);

        Intent it = getIntent();
        filme = it.getParcelableExtra("filme");

        editTitulo = (EditText) findViewById(R.id.editTitulo);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);

        editTitulo.setText(filme.getTitulo());

        carregarSpinner();

        spinnerGenero.setSelection(generos.indexOf(filme.getGenero()));
    }

    private void carregarSpinner() {
        GeneroDAO generoDAO = new GeneroDAO(this);
        Genero genero = new Genero();

        generos = generoDAO.carregaDadosLista();

        // Criando adaptador para o spinner
        ArrayAdapter<Genero> dataAdapter = new ArrayAdapter<Genero>(this,
                android.R.layout.simple_spinner_item, generos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Anexando adaptador de dados para o spinner
        spinnerGenero.setAdapter(dataAdapter);

    }

    public void salvarFilme(View v) {
        FilmeDAO filmeDAO = new FilmeDAO(this);

        Genero genero = (Genero) spinnerGenero.getSelectedItem();

        filme.setTitulo(editTitulo.getText().toString());
        filme.setGenero(genero);

        System.out.println(genero.getId() + " : " + genero.getDescricao());

        long resultado = filmeDAO.atualizar(filme);

        if (resultado > 0) {
            exibirMensagem("Registro atualizado com sucesso!");
        }
        else {
            exibirMensagem("Erro ao atualizar o item.");
        }
    }

    private void exibirMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
