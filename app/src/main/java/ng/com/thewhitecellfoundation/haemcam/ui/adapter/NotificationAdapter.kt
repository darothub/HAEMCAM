package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ng.com.thewhitecellfoundation.haemcam.data.Appointment
import ng.com.thewhitecellfoundation.haemcam.databinding.NotificationRecyclerviewLayoutBinding

class NotificationAdapter(var listOfAppointments: ArrayList<Appointment>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = NotificationRecyclerviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        var appointmentData = listOfAppointments[position]

        holder.bind(appointmentData)
    }

    override fun getItemCount(): Int = listOfAppointments.size

    inner class NotificationViewHolder(val binding: NotificationRecyclerviewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appointments: Appointment) {
            binding.notificationDateTextView.text = appointments.date
            binding.notificationAppointmentTextView.text = appointments.appointment
            binding.doctorNameTextView.text = appointments.doctorName
        }
    }

    fun newAppointmentAdded(appointmentAdded: Appointment) {
        listOfAppointments.forEach {
            if (appointmentAdded.date == it.date) {
                this.listOfAppointments.add(
                    appointmentAdded.apply {
                        appointment
                        doctorName
                    }
                )
            }
            notifyDataSetChanged()
        }
    }
}
