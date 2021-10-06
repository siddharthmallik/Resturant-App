package com.example.finallyidontcare;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finallyidontcare.model.MenuModel;


import java.util.ArrayList;

public class MenuAdap extends RecyclerView.Adapter<MenuAdap.ViewMenu1>{

    ArrayList<MenuModel> list1;
    Context cnx1;


    public MenuAdap(ArrayList<MenuModel> list1, Context cnx1) {
        this.list1 = list1;
        this.cnx1 = cnx1;
    }
    @NonNull
    @Override
    public ViewMenu1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cnx1).inflate(R.layout.menu_recycler,parent,false);
        return new ViewMenu1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMenu1 holder, int position) {
        MenuModel ms=list1.get(position);
        if(holder.txt1.getText().toString().isEmpty()){
            holder.l1.setLayoutParams(new RelativeLayout.LayoutParams(0,0));
        }
        else
        {
            holder.txt1.setText(ms.getText1());
        }
        holder.txt2.setText(ms.getText2());
    }

    @Override
    public int getItemCount() {
        return list1.size();

    }


    public class ViewMenu1 extends RecyclerView.ViewHolder{
        TextView txt1,txt2;
        LinearLayout l1;

        public ViewMenu1(@NonNull View itemView) {
            super(itemView);
                l1=itemView.findViewById(R.id.linear1);
                txt1 = itemView.findViewById(R.id.textView5);
                txt2 = itemView.findViewById(R.id.textView6);
            }
        }

}
