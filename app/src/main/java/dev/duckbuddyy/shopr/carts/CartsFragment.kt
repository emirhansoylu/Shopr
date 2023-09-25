package dev.duckbuddyy.shopr.carts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.duckbuddyy.shopr.R

class CartsFragment : Fragment() {

    companion object {
        fun newInstance() = CartsFragment()
    }

    private lateinit var viewModel: CartsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}