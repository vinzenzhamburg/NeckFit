package com.example.neckfit.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neckfit.R
import com.example.neckfit.databinding.FragmentSignupBinding
import com.example.neckfit.ui.MainViewModel

class SignupFragment : Fragment() {

    private lateinit var binding : FragmentSignupBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_signup,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.toast.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.signupSignupButton.setOnClickListener {
            signUp()
        }

        binding.signupCancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun signUp() {

        val email = binding.signupMail.text.toString()
        val password = binding.signupPassword.text.toString()

        if(!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            viewModel.signUp(email,password)
        }
    }
}