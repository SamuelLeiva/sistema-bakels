package com.example.proyectofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.database.ProductData;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    ArrayList<ProductData> productList;

    public ProductListAdapter(ArrayList<ProductData> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.product_card_fragment), null, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.name.setText(productList.get(position).getName());
        holder.brand.setText(productList.get(position).getBrand());
        holder.category.setText(productList.get(position).getCategory());
        holder.stock.setText("Stock: "+productList.get(position).getStock()+"");
        holder.price.setText("S/"+productList.get(position).getPrice() +"");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, brand, category, stock, price;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            brand = itemView.findViewById(R.id.product_brand);
            category = itemView.findViewById(R.id.product_category);
            stock = itemView.findViewById(R.id.product_stock);
            price = itemView.findViewById(R.id.product_price);
        }
    }
}
