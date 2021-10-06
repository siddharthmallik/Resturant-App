package com.example.finallyidontcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finallyidontcare.Adaptr.RecyAdap;
import com.example.finallyidontcare.model.RModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

  RecyclerView recviw;
  ImageButton m1,f1,r2,o1,ab1;

  FirebaseAuth Hauth;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_home_page);

   Hauth=FirebaseAuth.getInstance();

    recviw=findViewById(R.id.recview);
    ArrayList<RModel> list1=new ArrayList<>();

    list1.add(new RModel(R.drawable.b1,"Chicken Burger"));
    list1.add(new RModel(R.drawable.b2,"Chicken Burger"));
    list1.add(new RModel(R.drawable.p1,"Cheezy Pizza"));
    list1.add(new RModel(R.drawable.p2,"Cheezy Pizza"));
    list1.add(new RModel(R.drawable.pa1,"Spicy Pasta"));
    list1.add(new RModel(R.drawable.pa2,"Spicy Pasta"));
    list1.add(new RModel(R.drawable.pan1,"Soft Paneer"));
    list1.add(new RModel(R.drawable.pan2,"Soft Paneer"));

    RecyAdap adap1 = new RecyAdap(list1,HomePage.this);
    recviw.setAdapter(adap1);


    LinearLayoutManager layoutManager = new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false);
    recviw.setLayoutManager(layoutManager);

    o1=findViewById(R.id.imageButton4);
    m1=findViewById(R.id.ListMenu);
    f1=findViewById(R.id.imageButton6);
    r2=findViewById(R.id.imageButton3);
    ab1=findViewById(R.id.imageButton5);

      ab1.setOnClickListener(view -> {
          Intent men=new Intent(HomePage.this,ResInfo.class);
          startActivity(men);
      });

    m1.setOnClickListener(view -> {
      Intent men=new Intent(HomePage.this,MenuItems.class);
      startActivity(men);
    });


    f1.setOnClickListener(view -> {
      Intent i =new Intent(HomePage.this,FeedBack.class);
      startActivity(i);
    });
    r2.setOnClickListener(view -> {
      Intent i =new Intent(HomePage.this,ReserveTable.class);
      startActivity(i);
    });
    o1.setOnClickListener(view -> {
      Intent i =new Intent(HomePage.this,FoodOrder.class);
      startActivity(i);
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
              Hauth.signOut();
              Intent loki=new Intent(this,Login.class);
              startActivity(loki);
      }
      return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Hauth.signOut();
        Intent bk=new Intent(this,MainActivity.class);
        startActivity(bk);
    }
}