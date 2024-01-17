package com.palash.biometricauth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.palash.biometricauth.R
import com.palash.biometricauth.databinding.FragmentHomeBinding
import com.palash.biometricauth.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        authViewModel.authenticationStatus.observe(viewLifecycleOwner, Observer {
            if (it){
                // Handle successful authentication
                Toast.makeText(context, "Authentication successful", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_homeFragment_to_secondFragment)
            }else{
                // Handle authentication failure
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAuth.setOnClickListener {
            activity?.let { it1 -> authViewModel.authenticate(it1) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}