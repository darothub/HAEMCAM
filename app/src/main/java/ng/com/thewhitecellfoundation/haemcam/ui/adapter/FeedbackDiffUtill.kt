package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import ng.com.thewhitecellfoundation.haemcam.model.FeedBack

class FeedbackDiffUtill(
    private val oldList: List<FeedBack>,
    private val newList: List<FeedBack>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name === newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].description == newList[newItemPosition].description
    }
}
