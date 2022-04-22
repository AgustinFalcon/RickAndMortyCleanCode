package com.example.practicesealedclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicesealedclass.adapter.PersonajeAdapter
import com.example.practicesealedclass.databinding.FragmentHomeBinding
import com.example.practicesealedclass.viewmodels.PersonajeViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var personajeAdapter: PersonajeAdapter
    private val personajeViewModel: PersonajeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpRecyclerView();

        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




    private fun setUpRecyclerView() {
        personajeAdapter = PersonajeAdapter()
        binding.rvFragmentHome.apply {
            adapter = personajeAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        personajeViewModel.personajeLiveDataSuccess.observe(viewLifecycleOwner) {
            personajeAdapter.personajes = it
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }
}