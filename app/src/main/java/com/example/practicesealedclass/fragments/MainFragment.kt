package com.example.practicesealedclass.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.practicesealedclass.databinding.FragmentMainBinding
import com.example.practicesealedclass.viewmodels.ApiViewModel
import com.example.practicesealedclass.viewmodels.Estados
import com.example.practicesealedclass.viewmodels.MainViewModel


class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val personajeViewModel: ApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)


        binding.btnSiguiente.setOnClickListener {
            mainViewModel.validate(binding.edUser.text.toString(), binding.edPass.text.toString())
            personajeViewModel.setUrl("https://rickandmortyapi.com/api/")
            personajeViewModel.getPersonaje()
        }

        mainViewModel.liveData.observe(viewLifecycleOwner) {
            when (it) {
                Estados.EMPTY -> Toast.makeText(requireContext(), "The fields cannot be empty", Toast.LENGTH_SHORT).show()
                Estados.ERROR -> Toast.makeText(requireContext(), "User or Password incorrect", Toast.LENGTH_SHORT).show()
                Estados.SUCCESS -> Toast.makeText(requireContext(), "Bienvenido", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}