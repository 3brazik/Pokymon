package com.example.pokymon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokymon.Model.Pokemon;
import com.example.pokymon.R;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private ArrayList<Pokemon> mList = new ArrayList<>();
    private Context mContext;

    public PokemonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item,parent,false));
}

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        holder.tv.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<Pokemon> postsList) {
        this.mList = postsList;
        notifyDataSetChanged();
    }

public class PokemonViewHolder extends RecyclerView.ViewHolder {
   private TextView tv;
   private ImageView imageView;

    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);
        tv=itemView.findViewById(R.id.pokemon_tv);
        imageView=itemView.findViewById(R.id.pokemon_iv);


    }
}
}
