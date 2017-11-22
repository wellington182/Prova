package dominando.android.netfilmes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dominando.android.netfilmes.DAO.UsuarioDAO;
import dominando.android.netfilmes.Model.Usuario;
import dominando.android.netfilmes.R;
import dominando.android.netfilmes.Util.Util;

public class RegistroActivity extends AppCompatActivity {

    private EditText editLogin;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editLogin = (EditText) findViewById(R.id.editLoginUsuarioReg);
        editSenha = (EditText) findViewById(R.id.editSenhaUsuarioReg);
    }

    public void gravarUsuario(View v) {
        Usuario usuario = new Usuario();
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        usuario.setLogin(login);
        usuario.setSenha(Util.toMD5(senha));

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        long resultado = usuarioDAO.insereDado(usuario);

        if (resultado > 0) {
            Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Usuário não cadastrado.", Toast.LENGTH_SHORT).show();
        }

    }
}
