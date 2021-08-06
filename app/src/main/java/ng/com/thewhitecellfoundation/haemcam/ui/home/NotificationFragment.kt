package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentNotificationBinding
import ng.com.thewhitecellfoundation.haemcam.model.DoctorAppointment
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.NotificationAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment(R.layout.fragment_notification) {
    private val binding by viewBinding(FragmentNotificationBinding::bind)

    lateinit var notificationAdapter: NotificationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val notificationRecyclerView = binding.notificationRecyclerview

        notificationAdapter = NotificationAdapter(DoctorAppointment)
        notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        notificationRecyclerView.adapter = notificationAdapter
    }
}
