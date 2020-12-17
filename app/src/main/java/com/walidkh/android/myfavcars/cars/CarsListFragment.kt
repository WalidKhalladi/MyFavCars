package com.walidkh.android.myfavcars.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.walidkh.android.myfavcars.R
import com.walidkh.android.myfavcars.SharedViewModel
import com.walidkh.android.myfavcars.databinding.FragmentCarsListBinding
import com.walidkh.android.myfavcars.databinding.ItemCarLayoutBinding
import com.walidkh.android.myfavcars.models.Car


class CarsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val bindingView = DataBindingUtil
            .inflate<FragmentCarsListBinding>(
                inflater,
                R.layout.fragment_cars_list,
                container,
                false
            )

        val sharedViewModel = ViewModelProvider(requireActivity())
            .get(SharedViewModel::class.java)

        val carsListViewModel = ViewModelProvider(requireActivity())
            .get(CarsListViewModel::class.java)

        bindingView.sharedViewModel = sharedViewModel
        bindingView.carsListViewModel = carsListViewModel
        bindingView.lifecycleOwner = requireActivity()

        carsListViewModel.navigateToAddPage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    this.findNavController()
                        .navigate(CarsListFragmentDirections.actionCarsListFragmentToAddCarFragment())
                    carsListViewModel.navigateToAddPageComplete()
                }
            }
        })

        sharedViewModel.carsListData.observe(viewLifecycleOwner, Observer {
            it?.let {
                val listOfCars = it

                listOfCars.forEach { car ->
                    addCarItem(inflater, bindingView, car)
                }
            }
        })

        return bindingView.root
    }

    private fun addCarItem(
        inflater: LayoutInflater,
        bindingView: FragmentCarsListBinding,
        car: Car
    ) {
        val binding = ItemCarLayoutBinding.inflate(inflater, bindingView.carsListContainer, true)
        binding.itemCarNameId.text = car.name
        binding.itemCarBrandId.text = car.brand
        binding.itemCarTypeId.text = car.type.name
        binding.itemCarPowerId.text = car.horsePower.toString()
        binding.itemCarDescriptionId.text = car.description

        binding.cardContainerId.setOnClickListener {
            Snackbar.make(bindingView.container, car.name, Snackbar.LENGTH_SHORT).show()
        }
    }


}