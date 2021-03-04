package com.canonicalexamples.myDono.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canonicalexamples.myDono.databinding.ItemOngBinding
import com.canonicalexamples.myDono.viewmodels.OngsListViewModel


class OngsListAdapter(private val viewModel: OngsListViewModel): RecyclerView.Adapter<OngsListAdapter.OngItemViewHolder>() {

    class OngItemViewHolder(private val viewModel: OngsListViewModel, binding: ItemOngBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val ongName = binding.ongName
        init {
            binding.root.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            viewModel.onClickItem(layoutPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OngItemViewHolder =
        OngItemViewHolder(viewModel, ItemOngBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: OngItemViewHolder, position: Int) {
        val ong = viewModel.getItem(position)
        holder.ongName.text = "${ong.name}"
    }

    override fun getItemCount(): Int = viewModel.numberOfItems
}
