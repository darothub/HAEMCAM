package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentFeedbackRecyclerviewRowLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.FeedBack

class FeedBackAdapter : RecyclerView.Adapter<FeedBackAdapter.ViewHolder>() {

    var feedBack: List<FeedBack> = emptyList()

    class ViewHolder(val binding: FragmentFeedbackRecyclerviewRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedBack: FeedBack) = with(binding) {
            feedbackRowLayoutNameTextview.text = feedBack.name
            feedbackRowLayoutDescriptionTextview.text = feedBack.description
            feedbackRowLayoutLikeCountsTextView.text = feedBack.likes.toString()
            feedbackRowLayoutCommentsCounts.text = feedBack.comment.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentFeedbackRecyclerviewRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFeedBack = feedBack[position]
        holder.bind(currentFeedBack)
    }

    override fun getItemCount(): Int {
        return feedBack.size
    }

    fun setData(newData: List<FeedBack>) {
        val diffUtil = FeedbackDiffUtill(feedBack, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        feedBack = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
