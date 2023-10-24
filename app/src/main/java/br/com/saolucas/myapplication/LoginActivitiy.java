package br.com.saolucas.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivitiy extends AppCompatActivity {
    EditText edLogin, edPass;
    Button btLogin;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_autentical);
        db = new DBHelper(this);
        edLogin = (EditText)findViewById(R.id.edLogin);
        edPass = (EditText)findViewById(R.id.edPass);
        btLogin = (Button)findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username=edLogin.getText().toString();
                String password=edPass.getText().toString();
                if(username.equals("")) {
                    Toast.makeText(LoginActivitiy.this, "Usuario não inserido, tente novamente", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(LoginActivitiy.this,"Senha não inserida, tente novamente",Toast.LENGTH_SHORT).show();
                }else{
                    String res = db.validarLogin(username,password);
                    if(res.equals("OK")){
                        Toast.makeText(LoginActivitiy.this,"Login OK !!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivitiy.this,"Login ou Senha errado(s)!!",Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
    }
}
