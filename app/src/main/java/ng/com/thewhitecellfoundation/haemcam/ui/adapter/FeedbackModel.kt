package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.* // ktlint-disable no-wildcard-imports
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentFeedbackRowItemBinding
import ng.com.thewhitecellfoundation.haemcam.model.FeedBack

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.fragment_feedback_row_item
)
class FeedbackModel @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {

    var binding: FragmentFeedbackRowItemBinding = FragmentFeedbackRowItemBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(data: FeedBack) {
//        binding.fragmentFeedbackRowItemTitleTextview.text = data.name
//        binding.fragmentFeedbackRowItemCommentTextview.text = data.message
//        binding.fragmentFeedbackRowItemLikeIconTextview.setImageResource(R.drawable.like_icon_favourite)
//        binding.fragmentFeedbackRowItemNumberoflikesTextview.text = data.likeCount.toString()
//        binding.fragmentFeedbackRowItemLikestextTextview.text = data.likeText
//        binding.fragmentFeedbackRowItemCommentIconTextview.setImageResource(R.drawable.comment_icon)
//        binding.fragmentFeedbackRowItemNumberofcommentsTextview.text = data.commentCount.toString()
//        binding.fragmentFeedbackRowItemCommenttextTextview.text = data.commentText
    }
}
