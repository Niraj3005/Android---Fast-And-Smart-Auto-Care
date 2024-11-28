package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.example.onroadremotevehicleassistantapp.Model.ProductModel;
import com.example.onroadremotevehicleassistantapp.R;
import com.example.onroadremotevehicleassistantapp.UpdateProducts;

import java.util.ArrayList;
import java.util.List;

public class SingleProductViewAdapter extends RecyclerView.Adapter<SingleProductViewAdapter.ViewHolder> implements Filterable {
    List<ProductModel> productList;
    List<ProductModel> productListFull;
    String name,email;
    Context context;

    public SingleProductViewAdapter(List<ProductModel> productList,String name,String email, Context context) {
        this.productList = productList;
        this.context = context;
        this.name=name;
        this.email=email;
        productListFull = new ArrayList<>(productList);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_single_product,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.proName.setText("Product Name : "+productList.get(position).getProductName());
        holder.proCategory.setText("Product Category : "+productList.get(position).getProductCategory());
        holder.proPrice.setText("Rs. : "+productList.get(position).getProductPrice());
        holder.proRating.setText(String.valueOf("Rating : "+productList.get(position).getProductRating()));
        holder.proImg.setImageBitmap(productList.get(position).getProductImage());
        holder.proUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    int sid=productList.get(position).getProductId();
                String str=String.valueOf(sid);
                String str1=productList.get(position).getProductName();
                String str2=productList.get(position).getProductCategory();
                String str3=productList.get(position).getProductPrice();
                String str4=productList.get(position).getProductDescr();
                int stqty=productList.get(position).getStockQuantity();
                String str5 = String.valueOf(stqty);
                holder.proImg.buildDrawingCache();
                Bitmap str6 = holder.proImg.getDrawingCache();


                Intent i = new Intent(view.getContext(), UpdateProducts.class);
                i.putExtra("id", str);
                i.putExtra("name", str1);
                i.putExtra("price", str3);
                i.putExtra("desc", str4);
                i.putExtra("qty", str5);
                i.putExtra("pic", str6);
                i.putExtra("adminname",name);
                i.putExtra("adminemail",email);
                context.startActivity(i);
            }
        });
        holder.proDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db=new DatabaseHelper(view.getContext());
                boolean success = db.deleteProduct(productList.get(position).getProductId());
                if (success == true) {
                    Toast.makeText(view.getContext(), "Product Deleted Successfully...!", Toast.LENGTH_SHORT).show();

                } else {
                }
                productList.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ProductModel item : productListFull) {
                    if (item.getProductName().toLowerCase().contains(filterPattern) || item.getProductCategory().toLowerCase().contains(filterPattern) ) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView proName,proPrice,proRating,proCategory;
        ImageView proImg;
        Button proUpdate,proDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proName = itemView.findViewById(R.id.view_pname);
            proCategory = itemView.findViewById(R.id.view_pcate);
            proPrice = itemView.findViewById(R.id.view_price);
            proRating = itemView.findViewById(R.id.view_prating);
            proImg = itemView.findViewById(R.id.view_pimg);
            proUpdate = itemView.findViewById(R.id.view_update);
            proDelete = itemView.findViewById(R.id.view_delete);


        }
    }
}
