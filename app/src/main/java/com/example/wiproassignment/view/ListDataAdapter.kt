package com.example.wiproassignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wiproassignment.R
import com.example.wiproassignment.databinding.ListItemBinding
import com.example.wiproassignment.model.Rows
import com.example.wiproassignment.utils.EspressoIdlingResource

class ListDataAdapter :
    RecyclerView.Adapter<ListDataAdapter.FactsViewHolder>() {

    private var facts: ArrayList<Rows> = ArrayList()

    fun updateFacts(newFacts: ArrayList<Rows>) {
        EspressoIdlingResource.increment()
        facts.clear()
        facts.addAll(newFacts)
        notifyDataSetChanged()
        EspressoIdlingResource.decrement()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)

        return FactsViewHolder(binding)
    }

    override fun getItemCount() = facts.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val item = facts[position]
        holder.bind(item)
    }

    class FactsViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Rows) {
            binding.apply {
                title = if(null == item.title || item.title.isEmpty()) "--" else item.title
                description = if(null == item.description || item.description.isEmpty()) "--" else item.description
                image = item.image ?: ""
            }
        }
    }
}