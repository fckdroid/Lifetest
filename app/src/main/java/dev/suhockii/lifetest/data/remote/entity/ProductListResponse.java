package dev.suhockii.lifetest.data.remote.entity;

import java.util.List;

import dev.suhockii.lifetest.model.Product;

public class ProductListResponse {
    private List<ProductResponse> products;

    public List<? extends Product> getProducts() {
        return products;
    }
}
