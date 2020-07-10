package com.example.firebaseteste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseteste.config.ConfiguracaoFirebase;
import com.example.firebaseteste.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText campoEmail,campoSenha;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        campoEmail = findViewById(R.id.id_logcampoemail_conteudo);
        campoSenha = findViewById(R.id.id_logcamposenha_conteudo);
    }

    public void logarUsuario(Usuario usuario){
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                }else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch(FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não cadastrado.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Email e senha não correspondem a um usuário.";
                    }catch (Exception e){
                        excecao = "Erro ao logar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void validarAutenticacaoUsuario(View view){

        String txtEmail = campoEmail.getText().toString();
        String txtSenha = campoSenha.getText().toString();

        //validar dados
        if(!txtEmail.isEmpty()){
            if(!txtSenha.isEmpty()){

                Usuario usuario = new Usuario();
                usuario.setEmail(txtEmail);
                usuario.setSenha(txtSenha);

                logarUsuario(usuario);

            }else{
                Toast.makeText(this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Email incorreto!", Toast.LENGTH_SHORT).show();
        }

    }

    public void abrirTelaCadastro(View view){
        startActivity(new Intent(LoginActivity.this,CadastroActivity.class));
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if(usuarioAtual != null){
            abrirTelaPrincipal();
        }
    }
}