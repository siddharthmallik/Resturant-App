package com.example.finallyidontcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedBack extends AppCompatActivity {

    TextView foodFeedback;
    RatingBar rbStars;
    EditText editText;
    Button btn;
    FirebaseAuth sg=FirebaseAuth.getInstance();
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root=db.getReference().child("feedbacks");
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        db=FirebaseDatabase.getInstance();
        btn=findViewById(R.id.btnSubmit);
        editText = findViewById(R.id.ed);
        foodFeedback = findViewById(R.id.foodFeedback);
        rbStars = findViewById(R.id.rbStars);

        rbStars.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if(rating==0)
            {
                foodFeedback.setText("Very Dissatisfied");
            }
            else if(rating==1)
            {
                foodFeedback.setText("Dissatisfied");
            }
            else if(rating==2 || rating==3)
            {

                foodFeedback.setText("OK");
            }
            else if(rating==4)
            {
                foodFeedback.setText("Satisfied");
            }
            else if(rating==5)
            {
                foodFeedback.setText("Very Satisfied");
            }

        });
        btn.setOnClickListener(view -> submitcheck());
    }

    private void submitcheck()
    {
        String fp=foodFeedback.getText().toString();
        String dt=editText.getText().toString();

        HashMap<String , String> usermap=new HashMap<>();
        usermap.put("Rating",fp);
        usermap.put("Feedback",dt);

        root.push().setValue(usermap);

        if(TextUtils.isEmpty(editText.getText().toString()))
        {
            Toast.makeText(this, "please write your feedback", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this,"your feedback has been submitted successfully",Toast.LENGTH_SHORT).show();
        }
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
                sg.signOut();
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