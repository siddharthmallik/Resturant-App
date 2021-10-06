package com.example.finallyidontcare.Adaptr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finallyidontcare.R;
import com.example.finallyidontcare.model.RModel;

import java.util.ArrayList;

public class RecyAdap extends RecyclerView.Adapter<RecyAdap.viewHolder>{

    ArrayList<RModel> list;
    Context cnx;

    public RecyAdap(ArrayList<RModel> list, Context cnx) {
        this.list = list;
        this.cnx = cnx;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cnx).inflate(R.layout.sample_recylarvw,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RModel md = list.get(position);
        holder.imgb.setImageResource(md.getPic());
        holder.txtV.setText(md.getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageButton imgb;
        TextView txtV;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgb = itemView.findViewById(R.id.imgBut);
            txtV = itemView.findViewById(R.id.textV);
        }
    }


}
