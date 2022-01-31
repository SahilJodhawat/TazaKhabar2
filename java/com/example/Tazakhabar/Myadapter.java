package com.example.Tazakhabar;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tazakhabar2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.viewholder> {
private TextView textView;
private ImageView imageView;
List<Resultarray> resultarrays;



public Myadapter(List<Resultarray> resultarrayList, Context context){
    this.resultarrays=resultarrayList;



}

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news,parent,false);
        RecyclerView.ViewHolder viewHolder;

    textView=view.findViewById(R.id.textView);
    imageView=view.findViewById(R.id.imageView);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
holder.textView.setText(" "+resultarrays.get(holder.getAdapterPosition()).getTitle());

    Picasso.get().load(resultarrays.get(position).getImageUrl()).into(holder.imageView);
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(v.getContext(), Uri.parse(resultarrays.get(holder.getAdapterPosition()).getLink()));
    }
});
    }


    @Override
    public int getItemCount() {
        return resultarrays.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
private TextView textView;
private ImageView imageView;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.imageView);

        }
    }
}
