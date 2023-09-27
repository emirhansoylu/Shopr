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

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val viewModel: ProductDetailViewModel by viewModels()

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val uiStateCollector: suspend (ProductDetailState) -> Unit = { state ->
        binding.apply {
            srlProductDetail.isVisible = state is ProductDetailState.Success
            srlProductDetail.isRefreshing = state == ProductDetailState.Loading
            layoutProductDetailLoading.root.isVisible = state == ProductDetailState.Loading
            layoutProductDetailError.root.isVisible = state == ProductDetailState.Error

            if(state is ProductDetailState.Success) {
                layoutProductDetail.apply {
                    tvProductDetailName.text = state.productDetail.name
                    tvProductDetailPrice.text = "$ ${state.productDetail.price}"
                    tvProductDetailDescription.text = state.productDetail.description
                    ivProductDetail.load(state.productDetail.image)
                }
            }
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
        uiStateFlow.collectLatestWhenStarted(viewLifecycleOwner, uiStateCollector)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}