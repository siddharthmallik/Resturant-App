package com.example.finallyidontcare;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.finallyidontcare.model.MenuModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuItems extends AppCompatActivity {

    RecyclerView mRec;
    FirebaseAuth men;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);

        men=FirebaseAuth.getInstance();
        mRec=findViewById(R.id.menuRec);
        ArrayList<MenuModel> list2=new ArrayList<>();


        list2.add(new MenuModel("Veg","Veg Pulav ..........150"));
        list2.add(new MenuModel(null,"Veg Biryani ..........150"));
        list2.add(new MenuModel(null,"Veg Kolhapuri ..........150"));
        list2.add(new MenuModel(null,"Veg Punjabi Thali ..........150"));



        MenuAdap adap2 = new MenuAdap(list2,this);
        mRec.setAdapter(adap2);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRec.setLayoutManager(layoutManager);

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
                men.signOut();
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