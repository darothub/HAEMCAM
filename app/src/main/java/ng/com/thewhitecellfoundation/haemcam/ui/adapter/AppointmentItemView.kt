package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.AppointmentItemLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.Appointment

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.appointment_item_layout
)
class AppointmentItemView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {

    var binding: AppointmentItemLayoutBinding = AppointmentItemLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    @ModelProp
    fun setData(data: Appointment?) {
        binding.doctorNameTextView.text = data?.doctorName
        binding.appointmentTitleTv.text = data?.appointment
    }
    @CallbackProp
    fun onClick(listener: OnClickListener?) {
        if (listener != null) {
            binding.root.setOnClickListener(listener)
        }
    }
}
