package com.walidkh.android.myfavcars.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.walidkh.android.myfavcars.R
import com.walidkh.android.myfavcars.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentInstructionsBinding>(
            inflater,
            R.layout.fragment_instructions,
            container,
            false)

        val instructionsViewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        dataBinding.instructionsViewModel = instructionsViewModel
        dataBinding.lifecycleOwner = this

        instructionsViewModel.navigateToCarsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController().navigate(
                        InstructionsFragmentDirections.actionInstructionsFragmentToCarsListFragment("")
                    )
                    instructionsViewModel.navigateToCarsListPageComplete()
                }
            }
        })

        return dataBinding.root
    }
}