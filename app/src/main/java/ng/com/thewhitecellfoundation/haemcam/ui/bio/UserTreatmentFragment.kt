package ng.com.thewhitecellfoundation.haemcam.ui.bio

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.views.dismissPowerViewDropDown
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserTreamentBinding
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.TitleAndListType
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
        val list = arrayListOf<DrugDays>()
        val list2 = arrayListOf<DrugDays>(DrugDays(arrayListOf("Drugs"), arrayListOf("Day1")))
        val listOfTitleAndListType = arrayListOf(
            TitleAndListType<DrugDays>(chemoTitle, list, getString(R.string.days_cycle)),
            TitleAndListType(otherDrugTitle, list, getString(R.string.time_of_medication))
        )

        binding.ercv.withModels {
            listOfTitleAndListType.forEach { tALT ->
                titleAndListTypeView {
                    Log.i("TitleObjec", "$tALT id ${tALT.id}")
                    id(tALT.id)
                    data(tALT)
                    if (tALT.id == 1.toLong()) {
                        onAddListener(DrugDays(arrayListOf("Drugs"), arrayListOf("Day1"), getString(R.string.days_cycle)))
                    } else {
                        onAddListener(DrugDays(arrayListOf("Drugs"), arrayListOf("Day1"), getString(R.string.time_of_medication)))
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dismissPowerViewDropDown(
            binding.cycleSpinner,
            binding.regimenSpinner,
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
