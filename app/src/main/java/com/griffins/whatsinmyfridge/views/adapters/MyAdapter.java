package com.griffins.whatsinmyfridge.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.griffins.whatsinmyfridge.R;
import com.griffins.whatsinmyfridge.activities.SecondActivity;
import com.griffins.whatsinmyfridge.models.entities.Food;
import com.griffins.whatsinmyfridge.models.entities.Global;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // private String[] list;  // Food Object List
    private ArrayList<Food> list;                     // Food Object List
    private String[] data1 = new String[100];         // name
    private String[] data2 = new String[100];         // expiration
    private String[] data3 = new String[100];         // location
    private String[] data4 = new String[100];         // amount
    private String[] data5 = new String[100];         // category
    private String[] data6 = new String[100];         // notes
    private int[] images = new int[100];              // item image
    private int[] images2 = new int[100];             // category icon
    Context context;

    public MyAdapter(Context ct, ArrayList<Food> notExpired) {
        context = ct;
        // list = notExpired.toArray(new String[notExpired.size()]);
        list = notExpired;

        // System.out.println(list.get(1).name);

        for (int i = 0; i < list.size(); i++) {
            data1[i] = list.get(i).getName();
            data2[i] = list.get(i).getExpiration();
            data3[i] = list.get(i).getLocation();
            data4[i] = list.get(i).getAmount();
            data5[i] = list.get(i).getCategory();
            data6[i] = list.get(i).getNotes();
            images[i] = list.get(i).getImage();
            images2[i] = list.get(i).getImage2();
        }
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3[position]);
        holder.myImage.setImageResource(images[position]);
        holder.myImage2.setImageResource(images2[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("data3", data3[position]);
                intent.putExtra("data4", data4[position]);
                intent.putExtra("data5", data5[position]);
                intent.putExtra("data6", data6[position]);
                intent.putExtra("myImage", images[position]);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;
        ImageView myImage;
        ImageView myImage2;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myText3 = itemView.findViewById(R.id.myText3);
            myImage = itemView.findViewById(R.id.myImageView);
            myImage2 = itemView.findViewById(R.id.myImageView2);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    public Food getItemAt(int position) {
        return Global.notExpired.get(position);
    }

}
