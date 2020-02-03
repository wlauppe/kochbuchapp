package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListAdaper(var items: List<String> = emptyList<String>(), var viewModel:DisplaySearchListViewmodel)
    : RecyclerView.Adapter<DisplaySearchListAdaper.DisplaySearchListViewHolder>() {

    var navController:NavController ? = null

    fun setNewItems(newItems: List<String>){
        items = newItems
        this.notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplaySearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         navController = parent.findNavController()

        val displaySearchListItemBinding = DisplaySearchlistListitemBinding.inflate(inflater, parent, false)
        return DisplaySearchListViewHolder(displaySearchListItemBinding)

         }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DisplaySearchListViewHolder, position: Int) {
        holder.displaySearchlistListitemBinding.value = items[position]

        holder.displaySearchlistListitemBinding.displaySearchlistLayoutItem.setOnClickListener{}
        holder.displaySearchlistListitemBinding.displaySearchlistLayoutItem.setOnClickListener {

        }
    }

    class DisplaySearchListViewHolder(var displaySearchlistListitemBinding: DisplaySearchlistListitemBinding)
        :RecyclerView.ViewHolder(displaySearchlistListitemBinding.root)

}