package com.example.practicesealedclass.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practicesealedclass.databinding.ItemRvFragmentHomeBinding
import com.example.practicesealedclass.network.parsedata.personaje.ParsePersonaje


class PersonajeAdapter : RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeAdapter.PersonajeViewHolder {
        return PersonajeViewHolder(ItemRvFragmentHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonajeAdapter.PersonajeViewHolder, position: Int) {
        val personaje = personajes[position]

        holder.binding.apply {
            tvName.text = personaje.name
            tvGender.text = personaje.gender
            tvSpecie.text = personaje.species
            tvStatus.text = personaje.status
        }
    }

    override fun getItemCount(): Int = personajes.size

    inner class PersonajeViewHolder(val binding: ItemRvFragmentHomeBinding) : RecyclerView.ViewHolder(binding.root)




    private val diffCallBack = object : DiffUtil.ItemCallback<ParsePersonaje>(){
        override fun areItemsTheSame(oldItem: ParsePersonaje, newItem: ParsePersonaje): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ParsePersonaje, newItem: ParsePersonaje): Boolean {
            return oldItem == newItem
        }
    }

    //Take the values of de list from HomeFragment
    private val differ = AsyncListDiffer(this, diffCallBack)
    var personajes: List<ParsePersonaje>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


}