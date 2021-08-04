package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.TransfusionItemLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.BloodGroupModel

class TransfusionBloodGroupView(var list: ArrayList<BloodGroupModel>) : RecyclerView.Adapter<TransfusionBloodGroupView.TransfusionBloodGroupViewHolder>() {

    inner class TransfusionBloodGroupViewHolder(private var binding: TransfusionItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindAllViews(position: Int) {
            val currentListItem = list[position]

            // BINDING ALL VIEWS
            binding.apply {
                transfusionItemLayoutTextView.text = currentListItem.bloodGroup
            }
        }

        fun bindOneView(position: Int) {

            // BINDING ALL VIEWS
            binding.apply {
                transfusionItemLayoutTextView.setTextColor(Color.parseColor("#FFFFFF"))
                transfusionItemLayoutTextView.setBackgroundResource(R.drawable.transfusion_blood_group_first_item_background)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransfusionBloodGroupViewHolder {
        val view = TransfusionItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransfusionBloodGroupViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TransfusionBloodGroupViewHolder, position: Int) {
        if (position == 0) {
            holder.bindOneView(position)
        } else {
            holder.bindAllViews(position)
        }
    }
}
