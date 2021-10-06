package com.example.finallyidontcare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mt;
    Button b,b2;
    DatabaseReference Dref3;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser us1=mt.getCurrentUser();
        if(us1!=null){
            Intent tat=new Intent(MainActivity.this,HomePage.class);
            startActivity(tat);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mt = FirebaseAuth.getInstance();
        Dref3 = FirebaseDatabase.getInstance().getReference();
        b=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);


        b.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this,Login.class);
            startActivity(i);
        });
        b2.setOnClickListener(view -> {
            Intent i=new Intent(MainActivity.this,Register.class);
            startActivity(i);
        });

    }
}