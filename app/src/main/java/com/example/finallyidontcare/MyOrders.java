package com.example.finallyidontcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MyOrders extends AppCompatActivity {

    FirebaseAuth myordr;
    DatabaseReference myDB;
    TextView taka;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        myordr=FirebaseAuth.getInstance();
        myDB= FirebaseDatabase.getInstance().getReference();
        taka=findViewById(R.id.userOrders);
        myDB.child("users").child(Objects.requireNonNull(myordr.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              st= Objects.requireNonNull(snapshot.child("orders").getValue()).toString();
              taka.setText(st);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.profile:
                Intent pro=new Intent(this,UserProfile.class);
                startActivity(pro);
                break;
            case R.id.home1:
                Intent homi=new Intent(this,HomePage.class);
                startActivity(homi);
                break;
            case R.id.orders:
                Intent oral=new Intent(this,MyOrders.class);
                startActivity(oral);
                break;
            case R.id.about:
                Intent abss=new Intent(this,ResInfo.class);
                startActivity(abss);
                break;
            case R.id.logout:
                myordr.signOut();
                Intent loki=new Intent(this,Login.class);
                startActivity(loki);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent bk=new Intent(this,HomePage.class);
        startActivity(bk);
    }
}