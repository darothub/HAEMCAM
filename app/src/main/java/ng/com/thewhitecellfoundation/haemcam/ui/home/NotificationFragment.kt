package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentNotificationBinding
import ng.com.thewhitecellfoundation.haemcam.model.listOfNotificationData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.notificationItemView
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : HomeBaseFragment(R.layout.fragment_notification) {
    private val binding by viewBinding(FragmentNotificationBinding::bind)
    override val toolLeftImageDrawable: Int?
        get() = R.drawable.ic_baseline_keyboard_backspace_24

    override val toolBarTitle: String?
        get() = getString(R.string.notification)
    override val leftAction: () -> Unit
        get() = {
            navigator.navController.popBackStack()
        }
    override val toolRightImageDrawable: Int
        get() = R.drawable.ic_settings_icon
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notificationRecyclerview.withModels {
            listOfNotificationData.forEach { notificationData ->
                notificationItemView {
                    id(notificationData.id)
                    data(notificationData)
                    appointmentList(notificationData.appointments)
                }
            }
        }
    }
}
