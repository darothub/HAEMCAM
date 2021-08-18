package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.tabs.TabLayoutMediator
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentSideEffectReportingBinding
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.ViewPagerAdapter
import ng.com.thewhitecellfoundation.haemcam.ui.home.ButtonAndProgressBarState

/**
 * A simple [Fragment] subclass.
 * Use the [SideEffectReportingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SideEffectReportingFragment : Fragment(R.layout.fragment_side_effect_reporting) {
    private val binding by viewBinding(FragmentSideEffectReportingBinding::bind)
    lateinit var adapter: ViewPagerAdapter
    lateinit var buttonAndProgressBarState: ButtonAndProgressBarState

    override fun onAttach(context: Context) {
        super.onAttach(context)
        buttonAndProgressBarState = requireActivity() as ButtonAndProgressBarState
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
//
    }

    override fun onStart() {
        super.onStart()
        buttonAndProgressBarState.buttonState("Analyse report") {
        }
    }

    private fun setupViewPager() {
        val listOfSideEffect = arrayListOf(
            BarEntry(
                2014f, 420f
            ),
            BarEntry(
                2015f, 470f
            ),
            BarEntry(
                2016f, 508f
            ),
            BarEntry(
                2017f, 660f
            ),
            BarEntry(
                2018f, 550f
            ),
            BarEntry(
                2019f, 630f
            ),
            BarEntry(
                2020f, 460f
            )
        )
        val fragList = arrayListOf(
            SideEffectGraphFragment.newInstance(listOfSideEffect),
            SideEffectGraphFragment.newInstance(listOfSideEffect),
            SideEffectGraphFragment.newInstance(listOfSideEffect)
        )

        adapter = ViewPagerAdapter(requireActivity(), fragList.size) {
            fragList[it]
        }

        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.weekly)
                1 -> tab.text = getString(R.string.monthly)
                2 -> tab.text = getString(R.string.yearly)
            }
        }.apply {
            attach()
        }
    }
}
