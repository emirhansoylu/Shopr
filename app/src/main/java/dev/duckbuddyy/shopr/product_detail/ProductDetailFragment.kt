package dev.duckbuddyy.shopr.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.duckbuddyy.shopr.databinding.FragmentProductDetailBinding
import dev.duckbuddyy.shopr.domain.collectLatestWhenStarted
import dev.duckbuddyy.shopr.model.ProductDetail

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val viewModel: ProductDetailViewModel by viewModels()

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val productDetailCollector: suspend (ProductDetail?) -> Unit = { productDetail ->
        initializeViews(productDetail = productDetail)
    }

    private val hasErrorCollector: suspend (Boolean) -> Unit = { hasError ->
        initializeViews(hasError = hasError)
    }

    private val loadingCollector: suspend (Boolean) -> Unit = { isLoading ->
        initializeViews(isLoading = isLoading)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
    }

    private fun initializeViews(
        productDetail: ProductDetail? = null,
        isLoading: Boolean = false,
        hasError: Boolean = false
    ) = binding.apply {
        productDetail?.let {
            layoutProductDetail.root.visibility = VISIBLE
            layoutProductDetail.textView.text = it.name
            layoutProductDetail.textView2.text = it.description
            return@apply
        }

        if (isLoading) {
            layoutProductDetail.root.visibility = GONE
            return@apply
        }

        if (hasError) {
            layoutProductDetail.root.visibility = GONE
        }
    }

    private fun initializeObservers() = viewModel.apply {
        productDetailFlow.collectLatestWhenStarted(viewLifecycleOwner, productDetailCollector)
        loadingFlow.collectLatestWhenStarted(viewLifecycleOwner, loadingCollector)
        hasErrorFlow.collectLatestWhenStarted(viewLifecycleOwner, hasErrorCollector)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}