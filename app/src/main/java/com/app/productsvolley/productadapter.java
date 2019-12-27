package com.app.productsvolley;


//we need 2 things
//recyclerview.adapter
//recyclerview.viewholder

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class productadapter extends  RecyclerView.Adapter<productadapter.productviewholder> {

     private Context mCtx;
     private List<product> productList;

    public productadapter(Context mCtx, List<product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public productviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.products,null);
        return new productviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull productviewholder holder, int position) {

        product product=productList.get(position);
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);
       // holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class productviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle,textViewDesc, textViewRating,textViewPrice;


        public productviewholder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDesc=itemView.findViewById(R.id.textViewShortDesc);
            textViewRating=itemView.findViewById(R.id.textViewRating);
            textViewPrice=itemView.findViewById(R.id.textViewPrice);

        }
    }

}
