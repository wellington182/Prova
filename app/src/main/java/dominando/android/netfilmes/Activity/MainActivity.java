package dominando.android.netfilmes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dominando.android.netfilmes.DAO.GeneroDAO;
import dominando.android.netfilmes.DAO.UsuarioDAO;
import dominando.android.netfilmes.Model.Genero;
import dominando.android.netfilmes.Model.Usuario;
import dominando.android.netfilmes.R;

public class MainActivity extends AppCompatActivity {

    public static Usuario usuarioLogado;
    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textWelcome = (TextView) findViewById(R.id.textWelcome);

        Intent resultado = getIntent();
        long id = resultado.getLongExtra("ID_USUARIO", 0);

        if (usuarioLogado == null) {
            usuarioLogado = new UsuarioDAO(this).carregaUsuarioPorID(id);
        }

        textWelcome.setText("Olá " + usuarioLogado.getLogin());
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

    public void deslogar(View v){
        usuarioLogado = null;
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
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
