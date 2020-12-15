package com.walidkh.android.myfavcars.login

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
import com.walidkh.android.myfavcars.databinding.FragmentLoginBinding
import com.walidkh.android.myfavcars.preferences.PreferencesProvider


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false)

        val preferencesProvider = PreferencesProvider(requireContext())
        val viewModelFactory = LoginViewModelFactory(preferencesProvider)
        val loginViewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)
        dataBinding.loginViewModel = loginViewModel
        dataBinding.lifecycleOwner = this

        observeWelcomePageNavigation(loginViewModel, dataBinding)

        observeCarsListNavigation(loginViewModel, dataBinding)

        observeErrors(loginViewModel, dataBinding)

        return dataBinding.root
    }

    private fun observeErrors(
        loginViewModel: LoginViewModel,
        dataBinding: FragmentLoginBinding
    ) {
        loginViewModel.showError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    Snackbar.make(dataBinding.loginContainer, it, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observeCarsListNavigation(
        loginViewModel: LoginViewModel,
        dataBinding: FragmentLoginBinding
    ) {
        loginViewModel.navigateToCarsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    val userName = dataBinding.editTextUsername.text.toString()
                    this.findNavController()
                        .navigate(
                            LoginFragmentDirections.actionLoginFragmentToCarsListFragment(
                                userName
                            )
                        )
                    loginViewModel.navigateToCarsListComplete()
                }
            }
        })
    }

    private fun observeWelcomePageNavigation(
        loginViewModel: LoginViewModel,
        dataBinding: FragmentLoginBinding
    ) {
        loginViewModel.navigateToWelcomePage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    val userName = dataBinding.editTextUsername.text.toString()
                    this.findNavController()
                        .navigate(
                            LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                                userName
                            )
                        )
                    loginViewModel.navigateToWelcomePageComplete()
                }

            }

        })
    }

}