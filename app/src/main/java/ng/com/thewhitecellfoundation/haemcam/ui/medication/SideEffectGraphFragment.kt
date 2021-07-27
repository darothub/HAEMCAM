package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentSideEffectGraphBinding

private const val SIDE_EFFECTS = "SIDE_EFFECTS"

/**
 * A simple [Fragment] subclass.
 * Use the [SideEffectGraphFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SideEffectGraphFragment : Fragment(R.layout.fragment_side_effect_graph) {
    private val binding by viewBinding(FragmentSideEffectGraphBinding::bind)
    lateinit var listOfSideEffect: ArrayList<BarEntry>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            listOfSideEffect = it.getParcelableArrayList(SIDE_EFFECTS)!!
        }
        setUpBarchartData(listOfSideEffect, "Side Effect")
    }

    private fun setUpBarchartData(list: ArrayList<BarEntry>, title: String) {
        val barDataSet = BarDataSet(list, title)
        barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 14f

        val barData = BarData(barDataSet)

        binding.barChart.setFitBars(true)
        binding.barChart.data = barData
        binding.barChart.description.text = "Example"
        binding.barChart.animateY(2000)
    }

    companion object {
        @JvmStatic
        fun newInstance(sideEffects: ArrayList<BarEntry>?) =
            SideEffectGraphFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(SIDE_EFFECTS, sideEffects)
                }
            }
    }
}
