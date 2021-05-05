package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentChemoTherapyInfoBinding
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.chemoTherapyListView

/**
 * A simple [Fragment] subclass.
 * Use the [ChemoTherapyInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChemoTherapyInfo : Fragment(R.layout.fragment_chemo_therapy_info) {

    private val binding by viewBinding(FragmentChemoTherapyInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chemoTestString = arrayListOf<ChemoDrugTest>(ChemoDrugTest(1, "Drug 1"), ChemoDrugTest(2, "Drug 2"))

        binding.chemodrugErcv.withModels {
            chemoTestString.forEach { cdt ->
                chemoTherapyListView {
                    id(cdt.id)
                    data(cdt)
                }
            }
        }
    }
}

data class ChemoDrugTest(val id: Long, val str: String)
