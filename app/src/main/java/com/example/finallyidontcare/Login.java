package com.example.finallyidontcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText em1,ps1;
    Button s1;
    FirebaseAuth Mauth;
    DatabaseReference Dref2;


    public boolean isEmailValid(CharSequence email){
        if(email==null){
            return false;
        }else{
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mauth = FirebaseAuth.getInstance();
        Dref2 = FirebaseDatabase.getInstance().getReference();
        em1=findViewById(R.id.editEmailT);
        ps1=findViewById(R.id.editPassT);
        s1=findViewById(R.id.signIn);
        s1.setOnClickListener(view -> {
            if(em1.getText().toString().isEmpty()){
                Toast.makeText(Login.this,"Enter The Email" , Toast.LENGTH_SHORT).show();
            }else if(!isEmailValid(em1.getText().toString())){
                Toast.makeText(Login.this,"Enter Valid Email" , Toast.LENGTH_SHORT).show();
            }else if(ps1.getText().toString().isEmpty()){
                Toast.makeText(Login.this,"Enter The Password" , Toast.LENGTH_SHORT).show();
            }else {
                Mauth.signInWithEmailAndPassword(em1.getText().toString(),ps1.getText().toString()).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent hm= new Intent(Login.this,HomePage.class);
                        startActivity(hm);
                    }
                }).addOnFailureListener(e -> Toast.makeText(Login.this,"Login UnSuccessful"+e.getMessage(),Toast.LENGTH_SHORT).show());

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent bk=new Intent(this,MainActivity.class);
        startActivity(bk);
    }
}