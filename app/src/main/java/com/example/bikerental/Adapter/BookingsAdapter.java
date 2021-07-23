package com.example.bikerental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bikerental.BookingDetailsActivity;
import com.example.bikerental.ItemDetailsActivity;
import com.example.bikerental.Model.BookingDetailsSchema;
import com.example.bikerental.Model.BookingsModel;
import com.example.bikerental.R;

import java.io.Serializable;
import java.util.List;

import javax.net.ssl.SSLEngineResult;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {
    List<BookingDetailsSchema> bookingList;
    Context context;

    public BookingsAdapter(List<BookingDetailsSchema> itemList, Context cntx){
        this.bookingList=itemList;
        this.context = cntx;

    }

    @NonNull
    @Override
    public BookingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookings_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsAdapter.ViewHolder holder, int position) {

       // holder.itemImage.setImageResource(bookingList.get(position).get
      //  holder.itemtxt.setText(bookingList.get(position).getBdId());
//        holder.tvPickupDate.setText(bookingList.get(position).getFromDate().toString());
        holder.tvBikeName.setText(bookingList.get(position).getBikeName());
        holder.tvBikeBrand.setText(bookingList.get(position).getBrandName());


        //image
        String propertyImage = bookingList.get(position).getBdImage();
        String img = "http://bikerent.oxfordcollege.edu.np/storage";
        //get index of c from public of public/documents/sXyBPygG0YNIfRraVGeUcFQyURcwoKVE928sw7kW.jpg
        int index = propertyImage.indexOf("c");
        //remove string before index of /
        String result = propertyImage.substring(index+1);
        //create new image link joining server url
        String newImageUrl = img.concat(result);


        Glide.with(context)
                .asBitmap()
                .load(newImageUrl)
                .into(holder.itemImage);

//        holder.tvReturnDate.setText(bookingList.get(position).getToDate());
//        int status = bookingList.get(position).getStatus();
       // Log.v("fvdxc", String.valueOf(bookingList.get(position).getStatus()));
        if(bookingList.get(position).getStatus() == 0){
            holder.tvStatus.setText("Pending");
        }else if(bookingList.get(position).getStatus() == 1){
            holder.tvStatus.setText("Booked");
            holder.tvStatus.setBackgroundResource(R.drawable.rounded_button_green);
        }else if(bookingList.get(position).getStatus() == 2){
            holder.tvStatus.setText("Completed");
            holder.tvStatus.setBackgroundResource(R.drawable.rounded_button_accent);
        }else{
            holder.tvStatus.setText("Canceled");
            holder.tvStatus.setBackgroundResource(R.drawable.rounded_button_red);
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemtxt,tvBikeName,tvBikeBrand;

        TextView tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBikeName = itemView.findViewById(R.id.tvName);
            tvBikeBrand = itemView.findViewById(R.id.tvBikeBrand);
            itemImage=itemView.findViewById(R.id.ivItem);
//            itemtxt=itemView.findViewById(R.id.tvName);
//            tvPickupDate=itemView.findViewById(R.id.tvPickupDate);
//            tvReturnDate=itemView.findViewById(R.id.tvReturnDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BookingDetailsActivity.class);
                    intent.putExtra("BookingItem", (Serializable) bookingList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
