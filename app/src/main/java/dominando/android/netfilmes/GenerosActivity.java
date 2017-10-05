package dominando.android.netfilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GenerosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generos);
    }

    public void carregaItemMenu(View v){
        switch (v.getId()){
            case R.id.btnInserir:
                carregarIntent(CadastrarGeneroActivity.class);
                break;
            case R.id.btnListar:
                carregarIntent(ListarGenerosActivity.class);
                break;
        }
    }

    private void carregarIntent(Class classe){
        Intent intent = new Intent(GenerosActivity.this,classe);
        startActivity(intent);
    }
}
