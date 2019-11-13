package dev.suhockii.lifetest.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import dev.suhockii.lifetest.R
import dev.suhockii.lifetest.di.AppInjector
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.model.ProductDetails
import dev.suhockii.lifetest.util.AppUtils
import dev.suhockii.lifetest.util.ui.FragmentRouter
import dev.suhockii.lifetest.util.ui.Visibility
import dev.suhockii.lifetest.util.ui.fragment.SnackbarFragment
import kotlinx.android.synthetic.main.fragment_details_content.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : SnackbarFragment(), DetailsView {
    private var product: Product by Delegates.notNull()

    @InjectPresenter
    lateinit var detailsPresenter: DetailsPresenter

    @Inject
    lateinit var fragmentRouter: FragmentRouter

    override val toolbarTitle: String
        get() = getString(R.string.fragment_details_title)

    override val showBackButton: Boolean
        get() = true

    override val layoutRes: Int
        @LayoutRes
        get() = R.layout.fragment_details

    @ProvidePresenter
    fun provideDetailsPresenter(): DetailsPresenter {
        return AppInjector.getAppComponent().detailsPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = arguments
        if (arguments != null) {
            product = arguments.getParcelable(SELECTED_PRODUCT_KEY)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBarImage = view.findViewById<ProgressBar>(R.id.details_progressbar_image)

        details_tv_name.text = product.name
        details_tv_price.text = product.price.toString()

        val ivProduct = view.findViewById<ImageView>(R.id.details_iv_product)
        Glide.with(ivProduct)
            .load(product.imageUrl)
            .listener(AppUtils.getGlideListener(progressBarImage))
            .into(ivProduct)
    }

    override fun showDetails(productDetails: ProductDetails) {
        details_tv_description.text = productDetails.description
    }

    override fun onFirstPresenterAttach() {
        detailsPresenter.loadDetailsFor(product)
    }

    override fun showProgressBar(@Visibility visibility: Int) {
        details_progressbar_description.visibility = visibility
    }

    companion object {

        const val SELECTED_PRODUCT_KEY = "product_key"

        fun newInstance(product: Product): DetailsFragment {
            val bundle = Bundle()
            bundle.putParcelable(SELECTED_PRODUCT_KEY, product)

            val fragment = DetailsFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}
