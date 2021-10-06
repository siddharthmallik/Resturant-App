package com.example.finallyidontcare;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ReserveTable extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText date_in;
    EditText time_in;
    Button res;
    FirebaseAuth Rauth;
    DatabaseReference RDref;
    String da,ti,r;
    Spinner spinner;

    Calendar calendar=Calendar.getInstance();
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_table);

        Rauth = FirebaseAuth.getInstance();
        RDref = FirebaseDatabase.getInstance().getReference();
        res=findViewById(R.id.button2);
        date_in=findViewById(R.id.dateinput);
        time_in=findViewById(R.id.timeinput);

        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);

        spinner=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(ReserveTable.this,R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(ReserveTable.this);

        date_in.setOnClickListener(view -> showDateDialog(date_in));

        time_in.setOnClickListener(view -> showTimeDialog(time_in));


        res.setOnClickListener(view -> {
            r = spinner.getSelectedItem().toString();
            da = date_in.getText().toString();
            ti = time_in.getText().toString();
            if(r.isEmpty()){
                Toast.makeText(ReserveTable.this,"Please Select No. of People You Want to Reserve Table",Toast.LENGTH_SHORT).show();
            }else if(da.isEmpty()){
                Toast.makeText(ReserveTable.this,"Please Select A Date",Toast.LENGTH_SHORT).show();
            }else if(ti.isEmpty()){
                Toast.makeText(ReserveTable.this,"Please Select Time",Toast.LENGTH_SHORT).show();
            }else{
                RDref.child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        RDref.child("users").child(Objects.requireNonNull(Rauth.getCurrentUser()).getUid()).child("reserve").setValue(r+" \n"+da+"\n"+ti).addOnCompleteListener(task -> Toast.makeText(ReserveTable.this, "Your Table Has Been Reserved", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(ReserveTable.this,"Failed To Reserve Table"+e,Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text=adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showTimeDialog(final EditText time_in)
    {

        TimePickerDialog.OnTimeSetListener timeSetListener= (view, hourofday, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY,hourofday);
            calendar.set(Calendar.MINUTE,minute);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
            time_in.setText(simpleDateFormat.format(calendar.getTime()));

        };
        new TimePickerDialog(ReserveTable.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();

    }
    @SuppressLint("NewApi")
    private void showDateDialog(final EditText date_in)
    {

        DatePickerDialog.OnDateSetListener datesetlistener= (view, year, month, dayofmonth) -> {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayofmonth);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd");
            date_in.setText(simpleDateFormat.format(calendar.getTime()));


        };

        new DatePickerDialog(ReserveTable.this,datesetlistener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
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
                Rauth.signOut();
                Intent loki=new Intent(this,Login.class);
                startActivity(loki);
        }
        return super.onOptionsItemSelected(item);
    }
}