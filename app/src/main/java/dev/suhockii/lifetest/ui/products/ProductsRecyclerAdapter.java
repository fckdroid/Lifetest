package dev.suhockii.lifetest.ui.products;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.model.Product;

class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.RecyclerViewHolder> {
    private final View.OnClickListener onProductClickListener;
    private final List<Product> products;
    private OnItemClickListener onItemClickListener;

    ProductsRecyclerAdapter(List<Product> products) {
        this.products = products;
        onProductClickListener = view -> onItemClickListener.onItemClick(view);
    }

    ProductsRecyclerAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    Product getProductAt(int clickedPos) {
        return products.get(clickedPos);
    }

    interface OnItemClickListener {
        void onItemClick(View view);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        view.setOnClickListener(onProductClickListener);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));

        Picasso.with(holder.ivProduct.getContext())
                .load(product.getImageUrl())
                .fit()
                .noFade()
                .centerCrop()
                .into(holder.ivProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName;
        TextView tvPrice;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.item_iv_product);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPrice = itemView.findViewById(R.id.item_tv_price);
        }
    }
}
