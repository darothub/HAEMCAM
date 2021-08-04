package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ng.com.thewhitecellfoundation.haemcam.databinding.TransfusionMenuLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringModel

class TransfusionListView(var list: List<StringModel>) : RecyclerView.Adapter<TransfusionListView.TransfusionListViewHolder>() {
    inner class TransfusionListViewHolder(private val binding: TransfusionMenuLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindAllViews(position: Int) {
            val currentListItem = list[position]

            // BINDING ALL VIEWS
            binding.apply {
                transfusionMenuItemLayoutTextView.text = currentListItem.stringData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransfusionListViewHolder {
        val binding = TransfusionMenuLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransfusionListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TransfusionListViewHolder, position: Int) {
        holder.bindAllViews(position)
    }
}
