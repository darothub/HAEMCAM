package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.app.Dialog
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.NotificationLayoutDialogBinding
import ng.com.thewhitecellfoundation.haemcam.databinding.NotificationRecyclerviewLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.Appointment
import ng.com.thewhitecellfoundation.haemcam.model.NotificationData

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.notification_recyclerview_layout
)
class NotificationItemView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {

    var binding: NotificationRecyclerviewLayoutBinding = NotificationRecyclerviewLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(data: NotificationData?) {
        binding.notificationDateTv.text = data?.date
    }
    @ModelProp
    fun setAppointmentList(appointments: List<Appointment>) {
        binding.appointmentRcv.withModels {
            appointments.forEach { appointment ->
                appointmentItemView {
                    id(appointment.id)
                    data(appointment)
                    onClick { model, parentView, clickedView, position ->
                        val (b: NotificationLayoutDialogBinding, dialog) = bindDialog(
                            appointments,
                            position
                        )
                        b.closeDialogBtn.setOnClickListener {
                            dialog.dismiss()
                        }
                    }
                }
            }
        }
    }

    private fun bindDialog(
        appointments: List<Appointment>,
        position: Int
    ): Pair<NotificationLayoutDialogBinding, Dialog> {
        val b: NotificationLayoutDialogBinding =
            NotificationLayoutDialogBinding.inflate(LayoutInflater.from(context))
        val item = appointments[position]
        b.dialogDoctorNameTv.text = item.doctorName
        b.dialogTitleTv.text = item.appointment
        val dialog = Dialog(context)
        dialog.setCanceledOnTouchOutside(false)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(b.root)
            val window = this.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            window?.setGravity(Gravity.BOTTOM)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(this.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            window?.attributes?.windowAnimations = R.anim.elastic_up
            window?.attributes = lp
            show()
        }
        return Pair(b, dialog)
    }

    @CallbackProp
    fun onClick(listener: OnClickListener?) {
        if (listener != null) {
            binding.root.setOnClickListener(listener)
        }
    }
}
