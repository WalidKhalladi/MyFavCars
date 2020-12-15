package com.walidkh.android.myfavcars.info

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
import com.walidkh.android.myfavcars.R
import com.walidkh.android.myfavcars.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentWelcomeBinding>(
            inflater,
            R.layout.fragment_welcome,
            container,
            false)


        val arguments = WelcomeFragmentArgs.fromBundle(requireArguments())
        dataBinding.welcomeTitleId.text = getString(R.string.welcome_title, arguments.argUserName)

        val welcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        dataBinding.welcomeViewModel = welcomeViewModel
        dataBinding.lifecycleOwner = this

        welcomeViewModel.navigateToInstructionPage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController()
                        .navigate(
                            WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
                        )
                    welcomeViewModel.navigateToInstructionsPageComplete()
                }
            }
        })



        return dataBinding.root
    }
}