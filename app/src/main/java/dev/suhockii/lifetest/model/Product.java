package dev.suhockii.lifetest.model;

import android.os.Parcelable;
import androidx.annotation.NonNull;

public interface Product extends Parcelable {

    @NonNull
    String getId();
    String getName();
    int getPrice();
    String getImageUrl();
}
