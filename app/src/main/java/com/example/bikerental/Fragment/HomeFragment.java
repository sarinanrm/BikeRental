package com.example.bikerental.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bikerental.DateTimeActivity;
import com.example.bikerental.ItemDetailsActivity;
import com.example.bikerental.ItemListActivity;
import com.example.bikerental.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ImageView ivCategoryBike;
    ImageView ivCategoryScooter;
    Button btContinue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home,container,false);

        ivCategoryBike = v.findViewById(R.id.ivCategoryBike);
        ivCategoryBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ItemListActivity.class));
            }
        });

//        ivCategoryScooter = v.findViewById(R.id.ivCategoryScooter);
//        ivCategoryScooter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ItemListActivity.class));
//            }
//        });


        ImageSlider imageSlider = (ImageSlider) v.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://www.thenewsminute.com/sites/default/files/Bounce_bike_2-min.jpeg"));
        slideModels.add(new SlideModel("https://www.thenewsminute.com/sites/default/files/Bounce_bike_2-min.jpeg"));
        slideModels.add(new SlideModel("https://www.thenewsminute.com/sites/default/files/Bounce_bike_2-min.jpeg"));

        imageSlider.setImageList(slideModels,true);

        return v;

    }


}