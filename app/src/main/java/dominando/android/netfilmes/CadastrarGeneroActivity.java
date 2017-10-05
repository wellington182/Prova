package dominando.android.netfilmes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dominando.android.netfilmes.DAO.GeneroDAO;
import dominando.android.netfilmes.Model.Genero;

public class CadastrarGeneroActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_genero);

        editText = (EditText) findViewById(R.id.editDescricao);
    }

    public void salvarGenero(View view) {
        GeneroDAO generoDAO = new GeneroDAO(this);
        Genero genero = new Genero();

        genero.setDescricao(editText.getText().toString());

        long resultado = generoDAO.inserir(genero);

        if (resultado > 0) {
            exibirMensagem("Cadastro realizado com sucesso!");
        }
        else {
            exibirMensagem("Erro ao cadastrar item.");
        }
    }

    private void exibirMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
