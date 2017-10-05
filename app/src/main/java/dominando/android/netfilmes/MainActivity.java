package dominando.android.netfilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dominando.android.netfilmes.DAO.GeneroDAO;
import dominando.android.netfilmes.Factory.DataBaseFactory;
import dominando.android.netfilmes.Model.Genero;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void carregarMenu(View v) {
        switch (v.getId()) {
            case R.id.btnFilmes:
                if (verificar()) {
                    carregarIntent(FilmesActivity.class);
                }
                else {
                    Toast.makeText(this, "Cadastrar pelo menos um gênero!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnGeneros:
                carregarIntent(GenerosActivity.class);
                break;
        }
    }

    private void carregarIntent(Class classe) {
        Intent it = new Intent(MainActivity.this, classe);
        startActivity(it);
    }

    private boolean verificar() {
        List<Genero> generos = new ArrayList<>();

        GeneroDAO generoDAO = new GeneroDAO(this);
        generos = generoDAO.carregaDadosLista();

        if (generos.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
