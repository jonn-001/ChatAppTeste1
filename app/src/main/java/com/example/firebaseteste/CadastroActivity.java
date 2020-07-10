package com.example.firebaseteste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseteste.config.ConfiguracaoFirebase;
import com.example.firebaseteste.helper.Base64Custom;
import com.example.firebaseteste.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText campoNome,campoEmail,campoSenha;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.id_cadcamponome_conteudo);
        campoEmail = findViewById(R.id.id_cadcampoemail_conteudo);
        campoSenha = findViewById(R.id.id_cadcamposenha_conteudo);
    }

    public void cadastrarUsuario(final Usuario usuario){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,
                            "Sucesso ao cadastrar usuário!",
                            Toast.LENGTH_SHORT).show();
                    finish();

                    try{
                        String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                        usuario.setId(identificadorUsuario);
                        usuario.salvar();

                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um email válido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já está cadastrada!";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void validarCadastroUsuario(View view){

        String txtNome = campoNome.getText().toString();
        String txtEmail = campoEmail.getText().toString();
        String txtSenha = campoSenha.getText().toString();

        if(!txtNome.isEmpty()){
            if(!txtEmail.isEmpty()){
                if(!txtSenha.isEmpty()){
                    //Cadastro do usuário
                    Usuario usuario = new Usuario();
                    usuario.setNome(txtNome);
                    usuario.setEmail(txtEmail);
                    usuario.setSenha(txtSenha);

                    cadastrarUsuario(usuario);

                }else{
                    Toast.makeText(this, "Preencha a senha.", Toast.LENGTH_SHORT).show();

                }            }else{
                Toast.makeText(this, "Preencha o email.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o nome.", Toast.LENGTH_SHORT).show();
        }
    }


}