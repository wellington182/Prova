package dominando.android.netfilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FilmesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);
    }

    public void carregarMenu(View view) {
        switch (view.getId()){
            case R.id.btnInserir:
                carregarIntent(CadastrarFilmeActivity.class);
                break;
            case R.id.btnListar:
                carregarIntent(ListarFilmesActivity.class);
                break;
            case R.id.btnListarGenero:
                carregarIntent(ListarFilmesGeneroActivity.class);
                break;
        }
    }

    private void carregarIntent(Class classe) {
        Intent it = new Intent(FilmesActivity.this, classe);
        startActivity(it);
    }
}
