package ng.com.thewhitecellfoundation.haemcam.ui.lab

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentLabResultsBinding
import ng.com.thewhitecellfoundation.haemcam.databinding.YearPickerLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.ui.home.ButtonAndProgressBarState
import ng.com.thewhitecellfoundation.haemcam.ui.main.BaseFragment
import java.text.SimpleDateFormat
import java.util.*

class LabResultsFragment : BaseFragment(R.layout.fragment_lab_results) {
    private val binding by viewBinding(FragmentLabResultsBinding::bind)
    lateinit var description: Description
    lateinit var xAxis: XAxis

    lateinit var yearPickerLayoutBinding: YearPickerLayoutBinding
    override val buttonAndProgressBarState: ButtonAndProgressBarState by lazy {
        requireActivity() as ButtonAndProgressBarState
    }
    private val selectImageFromGalleryResult = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
//        uri?.let { previewImage.setImageURI(uri) }
    }
    private var latestTmpUri: Uri? = null

    override fun onStart() {
        super.onStart()
        buttonAndProgressBarState.buttonState(loading = false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yearPickerLayoutBinding = YearPickerLayoutBinding.inflate(layoutInflater)

        description = Description()
        description.text = ""
        description.textSize = 24f
        binding.lineChart.description = description
        xAxis = binding.lineChart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat: SimpleDateFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)
            override fun getFormattedValue(value: Float): String {
                val millis = value.toLong() * 1000L
                return mFormat.format(Date(millis))
            }
        }
        val yValues: ArrayList<Entry> = ArrayList()
        for (i in 0..10) {
            yValues.add(Entry(i.toFloat(), i.toFloat()))
        }
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        val lineDataSet = LineDataSet(yValues, "Blood test")
        lineDataSet.setDrawCircles(true)
        lineDataSet.circleRadius = 4F
        lineDataSet.setDrawValues(false)
        lineDataSet.lineWidth = 3F
        lineDataSet.color = Color.GREEN
        lineDataSet.setCircleColor(Color.GREEN)
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets)
        binding.lineChart.data = lineData
        binding.lineChart.invalidate()

        binding.yearSpinner.setItems((2021..2030).toList().map { it.toString() })

        binding.attachBtn.setOnClickListener {
            selectFile()
        }
    }

    private fun selectFile() = selectImageFromGalleryResult.launch(arrayOf("application/pdf"))
}
