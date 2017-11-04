package dev.suhockii.lifetest.data.remote.entity;

import java.util.List;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductList;

public class ProductListResponse implements ProductList {
    private List<ProductResponse> products;

    @Override
    public List<? extends Product> getProducts() {
        return products;
    }
}
