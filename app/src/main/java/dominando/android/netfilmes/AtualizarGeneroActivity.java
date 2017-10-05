package dominando.android.netfilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import dominando.android.netfilmes.DAO.GeneroDAO;
import dominando.android.netfilmes.Model.Genero;

public class AtualizarGeneroActivity extends AppCompatActivity {

    EditText editDescricao;
    List<Genero> generos;
    Genero genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_genero);

        Intent it = getIntent();
        genero = it.getParcelableExtra("genero");

        editDescricao = (EditText) findViewById(R.id.editDescricao);

        editDescricao.setText(genero.getDescricao());
    }

    public void salvarGenero(View v) {
        GeneroDAO generoDAO = new GeneroDAO(this);

        genero.setDescricao(editDescricao.getText().toString());

        System.out.println(genero.getId() + " : " + genero.getDescricao());

        long resultado = generoDAO.atualizar(genero);

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
