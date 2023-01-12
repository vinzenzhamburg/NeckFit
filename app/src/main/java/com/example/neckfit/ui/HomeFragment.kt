package com.example.neckfit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neckfit.R
import com.example.neckfit.databinding.FragmentHomeBinding


/**
 * Das MainFragment ist der Begrüßungsscreen unserer App
 * sollte kein User eingeloggt sein wird man automatisch zum Login weitergeleitet
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MainViewModel by activityViewModels()

    //private lateinit var userMail: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().navigate(R.id.loginFragment)
            } else {
                val mail = it.email.toString()
                binding.homeText.text = "Hey $mail! Nice to see you ;)"
            }
        }

        binding.startButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToThemenFragment())
        }
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }
    }
}