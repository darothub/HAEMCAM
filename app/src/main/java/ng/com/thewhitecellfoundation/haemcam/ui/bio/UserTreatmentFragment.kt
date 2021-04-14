package ng.com.thewhitecellfoundation.haemcam.ui.bio

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserTreamentBinding
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.TitleAndListType
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.DataPair
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.TitleAndListTypeView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.titleAndListTypeView

/**
 * A simple [Fragment] subclass.
 * Use the [UserTreamentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserTreatmentFragment : Fragment() {
    private var _binding: FragmentUserTreamentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserTreamentBinding.inflate(inflater, container, false)
        return binding.root
    }

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
                    Toast.makeText(context, "Some input(s) are nt complete", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Toast.makeText(context, "Complete $t", Toast.LENGTH_SHORT).show()
                Log.i("tdp", "$t")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
