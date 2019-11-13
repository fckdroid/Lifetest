package dev.suhockii.lifetest.ui.details;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import dev.suhockii.lifetest.util.AppUtils;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.di.AppInjector;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import dev.suhockii.lifetest.util.ui.FragmentRouter;
import dev.suhockii.lifetest.util.ui.Visibility;
import dev.suhockii.lifetest.util.ui.fragment.SnackbarFragment;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
    private ViewGroup viewGroup;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvName;
    private FloatingActionButton fabBack;
    private ProgressBar progressBarDescr;

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
        tvName = view.findViewById(R.id.details_tv_name);
        tvPrice = view.findViewById(R.id.details_tv_price);
        tvDescription = view.findViewById(R.id.details_tv_description);
        fabBack = view.findViewById(R.id.details_fab_back);
        viewGroup = view.findViewById(R.id.details_viewgroup);
        progressBarDescr = view.findViewById(R.id.details_progressbar_description);
        ProgressBar progressBarImage = view.findViewById(R.id.details_progressbar_image);

        //noinspection ConstantConditions
        fabBack.setOnClickListener(fabView -> getActivity().onBackPressed());
        tvName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        fabBack.setVisibility(View.INVISIBLE);
        tvName.setVisibility(View.INVISIBLE);
        tvDescription.setVisibility(View.INVISIBLE);
        tvPrice.setVisibility(View.INVISIBLE);

        ImageView ivProduct = view.findViewById(R.id.details_iv_product);
        ViewCompat.setTransitionName(ivProduct, product.getId());
        Glide.with(ivProduct)
                .load(product.getImageUrl())
                .listener(AppUtils.INSTANCE.getGlideListener(progressBarImage))
                .into(ivProduct);
    }

    @Override
    public void onResume() {
        super.onResume();
        Completable.timer(FragmentRouter.TRANSITION_DURATION, TimeUnit.MILLISECONDS)
                .doOnSubscribe(disposable -> getCompositeDisposable().add(disposable))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showUi);
    }

    private void showUi() {
        fabBack.show();
        TransitionManager.beginDelayedTransition(viewGroup, new Fade()
                .excludeTarget(R.id.details_iv_product, true));
        tvName.setVisibility(View.VISIBLE);
        tvDescription.setVisibility(View.VISIBLE);
        tvPrice.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetails(ProductDetails productDetails) {
        tvDescription.setText(productDetails.getDescription());
    }

    @Override
    public void onFirstPresenterAttach() {
        detailsPresenter.loadDetailsFor(product);
    }

    @Override
    public void showProgressBar(@Visibility int visibility) {
        progressBarDescr.setVisibility(visibility);
    }
}
