package dev.suhockii.lifetest.ui.products;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.util.AppUtils;

class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.RecyclerViewHolder> {
    private final List<Product> products;
    private OnProductClickListener onProductClickListener;

    ProductsRecyclerAdapter(List<Product> products) {
        this.products = products;
        onProductClickListener = view -> onProductClickListener.onItemClick(view);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        view.setOnClickListener(clickedView -> onProductClickListener.onItemClick(clickedView));

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));

        ImageView ivProduct = holder.ivProduct;

        Glide.with(ivProduct.getContext())
                .load(product.getImageUrl())
                .listener(AppUtils.getGlideListener(holder.progressBar))
                .into(ivProduct);

        ViewCompat.setTransitionName(ivProduct, product.getId());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    Product getProductAt(int clickedPos) {
        return products.get(clickedPos);
    }

    ProductsRecyclerAdapter setOnProductClickListener(OnProductClickListener onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
        return this;
    }

    interface OnProductClickListener {
        void onItemClick(View view);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName;
        TextView tvPrice;
        ProgressBar progressBar;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.item_iv_product);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPrice = itemView.findViewById(R.id.item_tv_price);
            progressBar = itemView.findViewById(R.id.item_progressbar);
        }
    }
}
