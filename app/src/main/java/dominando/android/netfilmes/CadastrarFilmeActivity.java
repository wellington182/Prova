package dominando.android.netfilmes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import dominando.android.netfilmes.DAO.FilmeDAO;
import dominando.android.netfilmes.DAO.GeneroDAO;
import dominando.android.netfilmes.Model.Filme;
import dominando.android.netfilmes.Model.Genero;

public class CadastrarFilmeActivity extends AppCompatActivity {

    private EditText editTitulo;
    private Spinner spinnerGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_filme);

        editTitulo = (EditText) findViewById(R.id.editTitulo);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);

        carregarSpinner();
    }

    private void carregarSpinner() {
        GeneroDAO generoDAO = new GeneroDAO(this);
        Genero genero = new Genero();

        List <Genero> generos = generoDAO.carregaDadosLista();

        // Criando adaptador para o spinner
        ArrayAdapter<Genero> dataAdapter = new ArrayAdapter<Genero>(this,
                android.R.layout.simple_spinner_item, generos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Anexando adaptador de dados para o spinner
        spinnerGenero.setAdapter(dataAdapter);

    }

    public void salvarFilme(View v) {
        FilmeDAO filmeDAO = new FilmeDAO(this);
        Filme filme = new Filme();

        Genero genero = (Genero) spinnerGenero.getSelectedItem();

        filme.setTitulo(editTitulo.getText().toString());
        filme.setGenero(genero);

        System.out.println(genero.getId() + " : " + genero.getDescricao());

        long resultado = filmeDAO.inserir(filme);

        if (resultado > 0) {
            exibirMensagem("Cadastro realizado com sucesso!");
        }
        else {
            exibirMensagem("Erro ao cadastrar o item.");
        }
    }

    private void exibirMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
