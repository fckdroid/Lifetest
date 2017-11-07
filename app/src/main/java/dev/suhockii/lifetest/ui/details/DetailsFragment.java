package dev.suhockii.lifetest.ui.details;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.di.AppInjector;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import dev.suhockii.lifetest.util.ui.FragmentRouter;
import dev.suhockii.lifetest.util.ui.fragment.SnackbarFragment;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static dev.suhockii.lifetest.util.ui.FragmentRouter.TRANSITION_DURATION;

/**
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends SnackbarFragment implements DetailsView {

    @InjectPresenter
    DetailsPresenter detailsPresenter;

    @Inject
    FragmentRouter fragmentRouter;

    @ProvidePresenter
    DetailsPresenter provideDetailsPresenter() {
        return AppInjector.getAppComponent().getDetailsPresenter();
    }

    public static final String SELECTED_PRODUCT_KEY = "product_key";

    private Product product;
    private ImageView ivProduct;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvName;
    private FloatingActionButton fabBack;

    public static DetailsFragment newInstance(Product product) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SELECTED_PRODUCT_KEY, product);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return getString(R.string.fragment_details_title);
    }

    @Override
    @LayoutRes
    protected int getLayoutRes() {
        return R.layout.fragment_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            product = arguments.getParcelable(SELECTED_PRODUCT_KEY);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivProduct = view.findViewById(R.id.details_iv_product);
        tvName = view.findViewById(R.id.details_tv_name);
        tvPrice = view.findViewById(R.id.details_tv_price);
        tvDescription = view.findViewById(R.id.details_tv_description);
        fabBack = view.findViewById(R.id.details_fab_back);

        ViewCompat.setTransitionName(ivProduct, product.getId());
        Glide.with(ivProduct)
                .load(product.getImageUrl())
                .into(ivProduct);

        tvName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        fabBack.setVisibility(View.INVISIBLE);
        fabBack.setOnClickListener(fabView -> getActivity().onBackPressed());
    }

    @Override
    public void onResume() {
        super.onResume();
        Completable.timer(TRANSITION_DURATION, TimeUnit.MILLISECONDS)
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> fabBack.show());
    }

    @Override
    public void showDetails(ProductDetails productDetails) {
        tvDescription.setText(productDetails.getDescription());
    }

    @Override
    public void onPresenterFirstAttach() {
        detailsPresenter.loadDetailsFor(product);
    }
}