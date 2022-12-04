package com.example.albertsonapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsonapplication.databinding.SearchItemBinding
import com.sample.project.models.LF


class SearchDataAdapter(private val context: Context, private val searchItems: MutableList<LF>) :
    RecyclerView.Adapter<SearchDataAdapter.ViewHolder>() {

    class ViewHolder(private val rowBinding: SearchItemBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {

        fun bindData(lf: LF) {
            rowBinding.title = lf.lf
            rowBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SearchItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(searchItems[position])
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    fun updateItems(results: List<LF>?) {

        searchItems.clear()
        searchItems.addAll(results!!.toMutableList())
        notifyDataSetChanged()


    }
}