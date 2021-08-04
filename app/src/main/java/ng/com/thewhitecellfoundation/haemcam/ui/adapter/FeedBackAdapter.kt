package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ng.com.thewhitecellfoundation.haemcam.databinding.FeedbackItemBinding
import ng.com.thewhitecellfoundation.haemcam.model.FeedBackItemData

// class FeedBackAdapter {
// }
class FeedBackAdapter(
    var photoArrayList: MutableList<FeedBackItemData>,
    var listener: RecyclerClickListener,
) : RecyclerView.Adapter<FeedBackAdapter.ViewHolder>() {
    // inner class
    inner class ViewHolder(val binding: FeedbackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        Binding the data with the view
        fun bind(feedBackItemData: FeedBackItemData) {
            binding.drName.text = feedBackItemData.drName
            binding.feedbackMessage.text = feedBackItemData.message
            binding.numbersOfLikes.text = feedBackItemData.numberOfLikes.toString()
            binding.numbersOfComments.text = feedBackItemData.numberOfComments.toString()
        }
    }
    // Creating view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FeedbackItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
    // Binding the view and attaching the listener
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoArrayList[position])
        holder.binding.likeHeartImage.setOnClickListener {
            listener.onItemClickToLike(holder.adapterPosition, photoArrayList)
        }
    }
    // Getting the item count size
    override fun getItemCount(): Int {
        return photoArrayList.size
    }
}
interface RecyclerClickListener {
    fun onItemClickToLike(position: Int, photoArrayList: MutableList<FeedBackItemData>)
}
