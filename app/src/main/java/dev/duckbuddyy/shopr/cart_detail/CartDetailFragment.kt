package dev.duckbuddyy.shopr.cart_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.duckbuddyy.shopr.R

class CartDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CartDetailFragment()
    }

    private lateinit var viewModel: CartDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}