package com.rini.bajuonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListBajuAdapter extends RecyclerView.Adapter<ListBajuAdapter.ListViewHolder>{

    private Context context;
    private ArrayList<Baju> listBaju;

    public ListBajuAdapter(Context context, ArrayList<Baju> listBaju){
    this.context = context;
    this.listBaju = listBaju;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
    this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListBajuAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baju,parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBajuAdapter.ListViewHolder holder, int position) {
        Baju baju = listBaju.get(position);
        Glide.with(holder.itemView.getContext())
                .load(baju.getGambar())
                .apply(new RequestOptions().override(50, 50))
                .into(holder.imgPhoto);

        holder.tvName.setText(baju.getNama());
        holder.tvHarga.setText(baju.getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listBaju.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBaju.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;
        TextView tvHarga;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_name);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvHarga = itemView.findViewById(R.id.tv_harga);
        }
    }
}
