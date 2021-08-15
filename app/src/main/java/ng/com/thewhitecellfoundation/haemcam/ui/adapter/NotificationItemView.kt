package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
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
                        val appointment = appointments[position]
                        val (b: NotificationLayoutDialogBinding, dialog) = bindDialog(
                            appointment
                        )
                        b.closeDialogBtn.setOnClickListener {
                            dialog.dismiss()
                        }
                        b.buttonProgressView.onClickActionListener {
                            addToCalendar(appointment)
                        }
                    }
                }
            }
        }
    }

    private fun bindDialog(
        appointment: Appointment
    ): Pair<NotificationLayoutDialogBinding, Dialog> {
        val b: NotificationLayoutDialogBinding =
            NotificationLayoutDialogBinding.inflate(LayoutInflater.from(context))
        val item = appointment
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

    private fun addToCalendar(appointment: Appointment) {
        val intent = Intent(Intent.ACTION_INSERT)
        intent.apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, appointment.appointment)
            putExtra(CalendarContract.Events.EVENT_LOCATION, "Hospital")
            putExtra(CalendarContract.Events.DESCRIPTION, "Default description")
            putExtra(CalendarContract.Events.ALL_DAY, true)
            putExtra(CalendarContract.Events.LAST_DATE, "21-08-2021")
            putExtra(Intent.EXTRA_EMAIL, "patient@patient.com, doctor@hospital.com")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Install calendar app", Toast.LENGTH_SHORT).show()
        }
    }
    @CallbackProp
    fun onClick(listener: OnClickListener?) {
        if (listener != null) {
            binding.root.setOnClickListener(listener)
        }
    }
}
