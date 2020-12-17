package com.walidkh.android.myfavcars.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.walidkh.android.myfavcars.R
import com.walidkh.android.myfavcars.SharedViewModel
import com.walidkh.android.myfavcars.databinding.FragmentAddCarBinding
import com.walidkh.android.myfavcars.databinding.FragmentCarsListBinding
import com.walidkh.android.myfavcars.models.Car

class AddCarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingView = DataBindingUtil
                .inflate<FragmentAddCarBinding>(
                        inflater,
                        R.layout.fragment_add_car,
                        container,
                        false
                )

        val sharedViewModel = ViewModelProvider(requireActivity())
                .get(SharedViewModel::class.java)

        val detailsViewModel = ViewModelProvider(this)
                .get(DetailsViewModel::class.java)

        bindingView.detailsViewModel = detailsViewModel
        bindingView.sharedViewModel = sharedViewModel
        bindingView.lifecycleOwner = this

        detailsViewModel.onConfirm.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {

                    if(sharedViewModel.addCar()) {

                        this.findNavController()
                            .navigate(
                                AddCarFragmentDirections.actionAddCarFragmentToCarsListFragment("")
                            )
                        detailsViewModel.onConfirmButtonNavigationComplete()
                    } else {
                        Snackbar.make(bindingView.detailsContainer, "Please fill all inputs", Snackbar.LENGTH_SHORT).show()
                    }

                }
            }
        })

        return bindingView.root
    }
}