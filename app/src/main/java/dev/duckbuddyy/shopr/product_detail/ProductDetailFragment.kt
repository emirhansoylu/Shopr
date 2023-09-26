package dev.duckbuddyy.shopr.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
        productDetail?.let {
            initializeViews(it)
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
        initializeObservers()
    }

    private fun initializeViews(productDetail: ProductDetail) = binding.apply {
        layoutProductDetail.textView.text = productDetail.name
        layoutProductDetail.textView2.text = productDetail.description
        //layoutProductDetail.textView2.text = productDetail.price.toString()
    }

    private fun initializeObservers() = viewModel.apply {
        productDetailFlow.collectLatestWhenStarted(viewLifecycleOwner, productDetailCollector)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}