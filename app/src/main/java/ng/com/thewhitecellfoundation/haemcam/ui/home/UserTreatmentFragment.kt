package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserTreamentBinding
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.TitleAndListType
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.TitleAndListTypeView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.titleAndListTypeView
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

/**
 * A simple [Fragment] subclass.
 * Use the [UserTreamentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserTreatmentFragment : Fragment(R.layout.fragment_user_treament) {
    private val binding by viewBinding(FragmentUserTreamentBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chemoTitle = getString(R.string.chemo_drug)
        val otherDrugTitle = getString(R.string.other_drugs)
        val regimenTitle = getString(R.string.regimen)
        val list = arrayListOf<DrugDays>()
        val listOfTitleAndListType = arrayListOf(
            TitleAndListType<DrugDays>(regimenTitle, list, getString(R.string.cycle_days)),
            TitleAndListType<DrugDays>(chemoTitle, list, getString(R.string.days_cycle)),
            TitleAndListType(otherDrugTitle, list, getString(R.string.time_of_medication))
        )

        binding.ercv.withModels {
            listOfTitleAndListType.forEach { tALT ->
                titleAndListTypeView {
                    Log.i("TitleObjec", "$tALT id ${tALT.id}")
                    id(tALT.id)
                    data(tALT)
                    when (tALT.title) {
                        chemoTitle -> {
                            onAddListener(DrugDays(getString(R.string.chemo_drug), R.array.diagnosis, R.array.stages, getString(R.string.days_cycle), DataPair("", "")))
                        }
                        otherDrugTitle -> {
                            onAddListener(DrugDays(getString(R.string.other_drugs), R.array.diagnosis, R.array.medication_time, getString(R.string.time_of_medication), DataPair("", "")))
                        }
                        regimenTitle -> {
                            onAddListener(DrugDays(getString(R.string.regimen), R.array.regimen, R.array.medication_time, getString(R.string.cycle_days), DataPair("", "")))
                        }
                    }
                }
            }
            binding.nextBtn.setOnClickListener {
                val t = TitleAndListTypeView.tdp
                t.values.forEach {
                    it.remove(DataPair("", ""))
                }
                val incomplete = t.entries.stream().anyMatch {
                    it.value.any { d -> d.first == "" || d.second == "" }
                }
                if (incomplete) {
                    Log.i("tdp", "$t")
                    Toast.makeText(context, "Some input(s) are nt complete", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Toast.makeText(context, "Complete $t", Toast.LENGTH_SHORT).show()
                Log.i("tdp", "$t")
                (requireActivity() as Navigator).goto(R.id.homeFragment)
            }
        }
    }
}
