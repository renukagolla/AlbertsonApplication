package com.example.albertsonapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsonapplication.databinding.SearchItemBinding
import com.example.albertsonapplication.model.MainLfS


class SearchAdapter(val searchItems: ArrayList<MainLfS>, val context: Context) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var filterData = ArrayList<MainLfS>()

    init {
        filterData = searchItems
    }
    fun updateData(filterItem: List<MainLfS>) {
        filterData.clear()
        this.filterData.addAll(filterItem)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SearchAdapter.SearchViewHolder {
        val viewBinding = SearchItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return SearchViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
//        holder.bind(searchItems.get(position).lfs.get(position))
        holder.bind(searchItems.get(position))
    }

    override fun getItemCount(): Int {
       /* if(filterData.size==0){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }else{

        }*/
        return searchItems.size
    }


    inner  class SearchViewHolder(private val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MainLfS){
            binding.lfModel=model
        }

    }

}