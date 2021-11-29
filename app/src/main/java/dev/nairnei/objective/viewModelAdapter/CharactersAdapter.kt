package dev.nairnei.objective.viewModelAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.nairnei.objective.databinding.InflateCharacterItemBinding
import dev.nairnei.objective.model.CharactersModel

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.StoreViewHolder>() {
    lateinit var storeModelList: CharactersModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            InflateCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class StoreViewHolder(val binding: InflateCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = storeModelList.data.results[position]
        holder.binding.apply {
            textView.text = store.name
            Glide
                .with(holder.itemView)
                .load(store.thumbnail.path + "." + store.thumbnail.extension)
                .centerCrop()
                .into(imageViewCharacter);

            holder.itemView.tag = "$position"
        }

    }

    override fun getItemCount(): Int {
        return storeModelList.data.count
    }




}