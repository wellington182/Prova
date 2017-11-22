package dominando.android.netfilmes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dominando.android.netfilmes.DAO.UsuarioDAO;
import dominando.android.netfilmes.R;
import dominando.android.netfilmes.Util.Util;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogin;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLogin = (EditText) findViewById(R.id.editLoginUsuario);
        editSenha = (EditText) findViewById(R.id.editSenhaUsuario);
    }

    public void logarUsuario(View v) {
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        long idUsuario = usuarioDAO.validaUsuario(login, Util.toMD5(senha));

        if (idUsuario > 0) {
            Toast.makeText(this, "Usuário Logado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("ID_USUARIO", idUsuario);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Usuário não cadastrado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarUsuario(View v){
        Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
        startActivity(intent);
    }
}
