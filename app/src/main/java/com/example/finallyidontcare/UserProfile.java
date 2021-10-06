package com.example.finallyidontcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserProfile extends AppCompatActivity {

    TextView tx1,tx2,tx3,tx4,tx5;
    FirebaseAuth NAuth;
    DatabaseReference NDref;
    FrameLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        NAuth = FirebaseAuth.getInstance();
        NDref = FirebaseDatabase.getInstance().getReference();
        tx1 = findViewById(R.id.userName);
        tx2 = findViewById(R.id.userName2);
        tx3 = findViewById(R.id.mobileN);
        tx4 = findViewById(R.id.emailT1);
        tx5 = findViewById(R.id.addr1);
        back=findViewById(R.id.backFrame);
        back.setOnClickListener(view -> {
            Intent home=new Intent(UserProfile.this,HomePage.class);
            startActivity(home);
        });
        NDref.child("users").child(Objects.requireNonNull(NAuth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = Objects.requireNonNull(snapshot.child("name").getValue()).toString();
                String email = Objects.requireNonNull(snapshot.child("email").getValue()).toString();
                String mobile = Objects.requireNonNull(snapshot.child("mobile").getValue()).toString();
                String address = Objects.requireNonNull(snapshot.child("address").getValue()).toString();
                tx1.setText(username);
                tx2.setText(username);
                tx3.setText(mobile);
                tx4.setText(email);
                if(address.isEmpty()){
                address = " NA ";
                tx5.setText(address);
                }
                tx5.setText(address);

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
                NAuth.signOut();
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