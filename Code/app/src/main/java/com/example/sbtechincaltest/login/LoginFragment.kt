package com.example.sbtechincaltest.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.sbtechincaltest.OnTextChangedWatcher
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        ).apply {
            logInButton.setOnClickListener { navigateToLogin() }
            usernameEditText.addTextChangedListener(OnTextChangedWatcher { verifyInputFields(this) })
            passwordEditText.addTextChangedListener(OnTextChangedWatcher { verifyInputFields(this) })
        }
        return binding.root
    }

    private fun navigateToLogin() {
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_photosFragment)
    }

    private fun verifyInputFields(binding: FragmentLoginBinding) {
        with(binding) {
            logInButton.isEnabled = !usernameEditText.text.isNullOrEmpty() && !passwordEditText.text.isNullOrEmpty()
        }
    }
}