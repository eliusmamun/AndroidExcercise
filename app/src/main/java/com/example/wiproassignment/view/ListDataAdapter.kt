package com.example.wiproassignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wiproassignment.R
import com.example.wiproassignment.databinding.ListItemBinding
import com.example.wiproassignment.model.Rows

class ListDataAdapter() :
    RecyclerView.Adapter<ListDataAdapter.FactsViewHolder>() {

    var facts: ArrayList<Rows> = ArrayList();

    fun updateFacts(newFacts: ArrayList<Rows>) {
        facts.clear()
        facts.addAll(newFacts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)

        return FactsViewHolder(binding)
    }

    override fun getItemCount() = facts.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val item = facts[position];
      //  if(item.title == null && item.description== null && item.image==null) return;
        holder.bind(item)
    }

    class FactsViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Rows) {
            binding.apply {
                title = item.title
                description = item.description
                image = item.image ?: ""
            }
        }
    }
}