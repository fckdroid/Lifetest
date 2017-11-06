package dev.suhockii.lifetest.model;

import android.support.annotation.NonNull;

public interface Product {

    @NonNull
    String getId();
    String getName();
    int getPrice();
    String getImageUrl();
}
