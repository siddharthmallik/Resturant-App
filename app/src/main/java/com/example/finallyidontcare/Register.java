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

import java.util.Objects;

public class Register extends AppCompatActivity {

    FirebaseAuth UserAuth;
    EditText name,email,mobile,pass,Cpass,addr;
    Button signUP;
    DatabaseReference Dref1;


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
        setContentView(R.layout.activity_register);

        UserAuth = FirebaseAuth.getInstance();
        Dref1 = FirebaseDatabase.getInstance().getReference();
        name = findViewById(R.id.editTextT1);
        email = findViewById(R.id.editEmail);
        mobile = findViewById(R.id.editTextT2);
        addr =findViewById(R.id.editAddress);
        pass = findViewById(R.id.editTextT3);
        Cpass = findViewById(R.id.editTextT4);
        signUP = findViewById(R.id.button);

        signUP.setOnClickListener(view -> {
            if(name.getText().toString().isEmpty()){
                Toast.makeText(Register.this,"Enter The Name" , Toast.LENGTH_SHORT).show();
            }else if(email.getText().toString().isEmpty()){
                Toast.makeText(Register.this,"Enter The Email" , Toast.LENGTH_SHORT).show();
            }else if(!isEmailValid(email.getText().toString())){
                Toast.makeText(Register.this,"Enter Valid Email" , Toast.LENGTH_SHORT).show();
            }else if(mobile.getText().toString().isEmpty()){
                Toast.makeText(Register.this,"Enter The Mobile Number" , Toast.LENGTH_SHORT).show();
            } else if(addr.getText().toString().isEmpty()){
                Toast.makeText(Register.this,"Enter Address" , Toast.LENGTH_SHORT).show();
            } else if(pass.getText().toString().isEmpty()){
                Toast.makeText(Register.this,"Enter The Password" , Toast.LENGTH_SHORT).show();
            }else if(Cpass.getText().toString().isEmpty()){
                Toast.makeText(Register.this,"Enter The Password To Confirm" , Toast.LENGTH_SHORT).show();
            }else if(Cpass.getText().toString().equals(pass.getText().toString())){
                UserAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Dref1.child("users").child(Objects.requireNonNull(UserAuth.getCurrentUser()).getUid()).child("name").setValue(name.getText().toString()).addOnCompleteListener(task1 -> Dref1.child("users").child(UserAuth.getCurrentUser().getUid()).child("email").setValue(email.getText().toString()).addOnCompleteListener(task11 -> Dref1.child("users").child(UserAuth.getCurrentUser().getUid()).child("mobile").setValue(mobile.getText().toString()).addOnCompleteListener(task111 -> Dref1.child("users").child(UserAuth.getCurrentUser().getUid()).child("address").setValue(addr.getText().toString()).addOnCompleteListener(task2 -> {
                            Toast.makeText(Register.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(Register.this, Login.class);
                            Register.this.startActivity(login);
                        }).addOnFailureListener(e -> Toast.makeText(Register.this, "Error in Signing Up " + e, Toast.LENGTH_SHORT).show()))));

                    }
                }).addOnFailureListener(e -> Toast.makeText(Register.this,"Error in Signing Up "+e.getMessage(),Toast.LENGTH_SHORT).show());
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