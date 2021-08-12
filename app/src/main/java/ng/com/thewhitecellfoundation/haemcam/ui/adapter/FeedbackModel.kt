package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
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
) : CardView(context, attr, defStyleAttr) {

    var binding: FragmentFeedbackRowItemBinding = FragmentFeedbackRowItemBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(data: FeedBack) {
        binding.apply {
            senderNameTv.text = data.senderName
            bodyTextTv.text = data.body
            likes.text = context.getString(R.string.no_of_likes, data.likes.toString())
            comment.text = context.getString(R.string.no_of_comment, data.comment.size.toString())
        }
    }
}
