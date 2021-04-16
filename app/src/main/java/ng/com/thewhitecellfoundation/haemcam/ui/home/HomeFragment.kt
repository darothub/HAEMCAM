package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentHomeBinding
import ng.com.thewhitecellfoundation.haemcam.model.HomeItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.homeItemView
import java.lang.Math.round

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfHomeItem = arrayListOf(
            HomeItemData(R.drawable.ic_medication_icon, R.string.medication),
            HomeItemData(R.drawable.ic_nutrition_icon, R.string.nutrition),
            HomeItemData(R.drawable.ic_lab_result_icon, R.string.lab_result),
            HomeItemData(R.drawable.ic_transfusion_icon, R.string.transfusion)
        )

        binding.ercv.withModels {

            listOfHomeItem.forEach { hid ->
                homeItemView {
                    id(hid.id)
                    data(hid)
                    spanSizeOverride { totalSpanCount, position, itemCount -> totalSpanCount / 2 }
                }
            }
        }
    }
}

class NumItemsInGridRow(context: Context?, forPhone: Int, forTablet: Int, forWideTablet: Int) :
    SpanSizeOverrideCallback {
    val numItemsForCurrentScreen: Int
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        check(totalSpanCount % numItemsForCurrentScreen == 0) { "Total Span Count of : $totalSpanCount can not evenly fit: $numItemsForCurrentScreen cards per row" }
        return totalSpanCount / numItemsForCurrentScreen
    }

    companion object {
        /** Shows one item per row on phone and two for all tablet sizes.  */
        fun oneColumnPhoneTwoColumnTablet(context: Context?): NumItemsInGridRow {
            return NumItemsInGridRow(context, 1, 2, 2)
        }

        /** Specify how many items to show per grid row on phone. Tablet will show more items per row according to a default ratio.  */
        fun forPhoneWithDefaultScaling(
            context: Context?,
            numItemsPerRowOnPhone: Int
        ): NumItemsInGridRow {
            return NumItemsInGridRow(
                context,
                numItemsPerRowOnPhone,
                round(numItemsPerRowOnPhone * 1.5f),
                numItemsPerRowOnPhone * 2
            )
        }
    }
    /**
     * Checks if the device is a tablet (7" or greater).
     */
    private fun checkIsTablet(context: Context?): Boolean {
        val display = (context as Activity).windowManager.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        val widthInches = metrics.widthPixels / metrics.xdpi
        val heightInches = metrics.heightPixels / metrics.ydpi
        val diagonalInches =
            Math.sqrt(Math.pow(widthInches.toDouble(), 2.0) + Math.pow(heightInches.toDouble(), 2.0))
        return diagonalInches >= 7.0
    }
    init {
        numItemsForCurrentScreen =
            if (checkIsTablet(context)) forTablet else forPhone
    }
}
