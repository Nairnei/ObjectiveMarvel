package dev.nairnei.objective.viewModelAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.nairnei.objective.databinding.InflatePageItemBinding
import dev.nairnei.objective.viewModel.HomeViewModel

class PaginationAdapter(homeViewModel: HomeViewModel) :
    RecyclerView.Adapter<PaginationAdapter.StoreViewHolder>() {

    var total: Int = 0
    var offSet: Int = 0
    var limit: Int = 4
    var selected: Boolean = false
    var countPage = 3
    var avalaible = total / limit ///1500 / 5  == 300

    var homeViewModel: HomeViewModel = homeViewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            InflatePageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class StoreViewHolder(val binding: InflatePageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {

//        if (avalaible < total && position < countPage && offSet + position >= 1) {
        holder.binding.apply {

            textViewPageNumber.text = position.toString()

            if ((position * limit) + 1 == offSet || offSet == 0 && position == 0) {
                textViewPageNumber.setTextColor(Color.parseColor("#AA0000"));

            }


            textViewPageNumber.setOnClickListener {
                selected = !selected
                if (selected) {
                    textViewPageNumber.setTextColor(Color.parseColor("#AA0000"));
                    homeViewModel.listShop(currentPage = (position * limit) + 1)
                } else {
                    textViewPageNumber.setTextColor(Color.parseColor("#AA0000"));
                    homeViewModel.listShop(currentPage = (position * limit) + 1)
                }
            }

            holder.itemView.tag = "$position"
        }

//        } else {
//            holder.binding.textViewPageNumber.visibility = View.GONE
//        }
    }

    override fun getItemCount(): Int {
        return total / limit

    }


}

///