package dev.duckbuddyy.shopr.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.duckbuddyy.shopr.databinding.FragmentProductsBinding
import dev.duckbuddyy.shopr.domain.collectLatestWhenStarted
import dev.duckbuddyy.shopr.model.Product

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private val viewModel: ProductsViewModel by viewModels()

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val productAdapter by lazy {
        ProductAdapter(
            onItemClicked = { navigateToProductDetail(product = it) }
        )
    }

    private val productsCollector: suspend (List<Product>) -> Unit = { products ->
        productAdapter.submitList(products)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initializeObservers()
    }

    private fun initializeViews() = binding.apply {
        rvProduct.adapter = productAdapter
    }

    private fun initializeObservers() = viewModel.apply {
        productsFlow.collectLatestWhenStarted(viewLifecycleOwner, productsCollector)
    }

    private fun navigateToProductDetail(product: Product) {
        val productId = product.productId.ifBlank { return }
        findNavController().navigate(
            ProductsFragmentDirections.actionCartsFragmentToCartDetailFragment(productId)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}