package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding

class RecipeListAdapter (var names: List<String>): RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> (){




    fun setNewNames(newNames:List<String>)
    {
        names = newNames
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {


        val inflater = LayoutInflater.from(parent.context)

        val itemBinding = RecipeListItemBinding.inflate(inflater, parent, false)

        return RecipeListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {

        holder.recipeListItemBinding.textViewRecipeTitleItem.setText(names[position])
        holder.recipeListItemBinding.buttonEditRecipe.setOnClickListener{
             //TODO fragment wechsel
        }
        holder.recipeListItemBinding.buttonRemoveRecipe.setOnClickListener{
            //TODO fragment wechsel
        }

        }


    class RecipeListViewHolder(val recipeListItemBinding: RecipeListItemBinding): RecyclerView.ViewHolder(recipeListItemBinding.root)


}