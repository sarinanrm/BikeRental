package com.example.bikerental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikerental.ItemDetailsActivity;
import com.example.bikerental.ItemListActivity;
import com.example.bikerental.Model.ItemModel;
import com.example.bikerental.R;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    public static final String EXTRA_ITEM = "extra_item";
    private WeakReference<Context> weakReference;
    List<ItemModel> itemList;
    Context mContext;
    //Context context;


    public ItemAdapter(List<ItemModel> itemList, Context context){
        this.itemList=itemList;
        this.weakReference = new WeakReference<Context>(context);
        mContext = context;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {

//        holder.itemImage.setImageResource(itemList.get(position).getImage());
        holder.tvPrice.setText("Rs."+itemList.get(position).getPrice() +" / day");
        holder.tvName.setText(itemList.get(position).getBikeName());
        holder.tvBrand.setText("Brand: "+itemList.get(position).getBrandName());
//
//        holder.tvPrice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(weakReference.get(), ItemDetailsActivity.class);
//
////                intent.putExtra("price",itemList.get(position).getPrice()+" / day");
////                intent.putExtra("bike_name",itemList.get(position).getBikeName());
////                intent.putExtra("brand_name",itemList.get(position).getBrandName());
////                Bundle bundle =new Bundle();
////
////                bundle.putString("bike_name",itemList.get(position).getName());
////                bundle.putString("brand_name",itemList.get(position).getBrand());
////                bundle.putString("price",itemList.get(position).getPrice());
//
//
//
////                bundle.putString("mileage",itemList.get(position).getMileage());
//
////                intent.putExtras(bundle);
//
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                weakReference.get().startActivity(intent);
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;

        TextView tvName, tvBrand, tvPrice, tvmileage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            itemImage=itemView.findViewById(R.id.ivItem);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvName=itemView.findViewById(R.id.tvName);
            tvBrand=itemView.findViewById(R.id.tvBrand);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //((ItemListActivity) weakReference.get()).ItemClick(getAdapterPosition());

                    Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                     intent.putExtra("Item", (Serializable) itemList.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                   // Log.v("sdrfvd","Clicked");
                }
            });

        }
    }
}
