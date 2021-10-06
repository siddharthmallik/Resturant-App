package com.example.finallyidontcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class FoodOrder extends AppCompatActivity {

    FirebaseAuth Mauth1;
    DatabaseReference DbaseRef;
    TextView amt;
    EditText addr;
    Button place;
    Spinner spinner1,spinner2;
    ArrayAdapter<String> myAdapter;
    ArrayAdapter<String> myAdapter2;
    String a,b;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);

        Mauth1 = FirebaseAuth.getInstance();
        DbaseRef = FirebaseDatabase.getInstance().getReference();
        amt = findViewById(R.id.textView4);
        addr = findViewById(R.id.address);
        place = findViewById(R.id.button4);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        myAdapter= new ArrayAdapter<>(FoodOrder.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);



        myAdapter2 = new ArrayAdapter<>(FoodOrder.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Payment));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                a = spinner1.getSelectedItem().toString();
                if(a.isEmpty()){
                    Toast.makeText(FoodOrder.this,"Please Select A Dish",Toast.LENGTH_SHORT).show();
                }
                else {
                    amt.setVisibility(View.VISIBLE);
                    amt.setText("150");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b=spinner2.getSelectedItem().toString();


        place.setOnClickListener(view -> {
            if(b.isEmpty()){
                Toast.makeText(FoodOrder.this,"Please Select A Payment Method",Toast.LENGTH_SHORT).show();
            }
            if(a.isEmpty()){
                Toast.makeText(FoodOrder.this,"Please Select A Dish",Toast.LENGTH_SHORT).show();
            }
            if(addr.getText().toString().isEmpty()){
                Toast.makeText(FoodOrder.this,"Please Enter Your Address",Toast.LENGTH_SHORT).show();
            }
            DbaseRef.child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DbaseRef.child("users").child(Objects.requireNonNull(Mauth1.getCurrentUser()).getUid()).child("orders").setValue(a+" "+amt.getText().toString()+" ").addOnCompleteListener(task -> DbaseRef.child("users").child(Objects.requireNonNull(Mauth1.getCurrentUser()).getUid()).child("address").setValue(addr.getText().toString()).addOnCompleteListener(task1 -> Toast.makeText(FoodOrder.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(FoodOrder.this,"Failed To Place Your Order",Toast.LENGTH_SHORT).show()));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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
                Mauth1.signOut();
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