package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.HomeItemListLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.HomeItemData

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.home_item_list_layout
)
open class HomeItemView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attr, defStyleAttr) {
    var binding: HomeItemListLayoutBinding = HomeItemListLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    @ModelProp
    fun setData(data: HomeItemData?) {
        data?.image?.let { binding.iconIv.setImageResource(it) }
        binding.titleTv.text = data?.title?.let { context.getString(it) }
    }

    @CallbackProp
    fun setOnClick(listener: OnClickListener?) {
        binding.root.setOnClickListener(listener)
    }
}
