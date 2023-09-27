package dev.duckbuddyy.shopr.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.duckbuddyy.shopr.databinding.FragmentProductDetailBinding
import dev.duckbuddyy.shopr.domain.collectLatestWhenStarted
import dev.duckbuddyy.shopr.domain.load
import dev.duckbuddyy.shopr.model.ProductDetail

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val viewModel: ProductDetailViewModel by viewModels()

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val productDetailCollector: suspend (ProductDetail?) -> Unit = { productDetail ->
        binding.apply {
            srlProductDetail.isVisible = productDetail != null
            productDetail?.let {
                layoutProductDetail.apply {
                    tvProductDetailName.text = it.name
                    tvProductDetailPrice.text = "$ ${it.price}"
                    tvProductDetailDescription.text = it.description
                    ivProductDetail.load(it.image)
                }
            }
        }
    }

    private val hasErrorCollector: suspend (Boolean) -> Unit = { hasError ->
        binding.layoutProductDetailError.root.isVisible = hasError
    }

    private val loadingCollector: suspend (Boolean) -> Unit = { isLoading ->
        binding.apply {
            layoutProductDetailLoading.root.isVisible = isLoading
            srlProductDetail.isRefreshing = isLoading
        }
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
        initializeViews()
        initializeObservers()
    }

    private fun initializeViews() = binding.apply {
        srlProductDetail.setOnRefreshListener { viewModel.refreshProductDetail() }
        layoutProductDetailError.retryButton.setOnClickListener { viewModel.refreshProductDetail() }
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