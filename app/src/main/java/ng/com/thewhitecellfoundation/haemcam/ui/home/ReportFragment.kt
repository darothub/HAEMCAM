package ng.com.thewhitecellfoundation.haemcam.ui.home

import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentReportBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportFragment : HomeBaseFragment(R.layout.fragment_report) {
    private val binding by viewBinding(FragmentReportBinding::bind)
}
