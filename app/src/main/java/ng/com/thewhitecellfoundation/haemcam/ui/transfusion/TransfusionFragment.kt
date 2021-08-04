package ng.com.thewhitecellfoundation.haemcam.ui.transfusion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentTransfusionBinding
import ng.com.thewhitecellfoundation.haemcam.model.BloodGroupModel
import ng.com.thewhitecellfoundation.haemcam.model.StringModel
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.TransfusionBloodGroupView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.TransfusionListView

class TransfusionFragment : Fragment(R.layout.fragment_transfusion) {
    private val binding by viewBinding(FragmentTransfusionBinding::bind)
    lateinit var transfusionAdapter: TransfusionBloodGroupView
    lateinit var transfusionListView: TransfusionListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transfusionBloodGroupAdapter()
        transfusionListView()
    }

    private fun transfusionBloodGroupAdapter() {
        // transaction blood group list
        val listOfBloodGroup = arrayListOf(
            BloodGroupModel("A+"),
            BloodGroupModel("A-"),
            BloodGroupModel("AB+"),
            BloodGroupModel("AB-"),
            BloodGroupModel("0+"),
            BloodGroupModel("0-"),
            BloodGroupModel("B+"),
            BloodGroupModel("B-")
        )
        transfusionAdapter = TransfusionBloodGroupView(listOfBloodGroup) // initializing adapter
        binding.fragmentTransactionBloodGroupEpoxyRecyclerview.layoutManager = GridLayoutManager(requireContext(), 4) // adding layout manager to epoxyrecyclerview
        binding.fragmentTransactionBloodGroupEpoxyRecyclerview.adapter = transfusionAdapter // Attaching adapter to the recyclerview

    }

    private fun transfusionListView() {
        // transaction menu items
        val listOfTransfusionMenuItem = arrayListOf(
            StringModel("Transfusion reactions/complications"),
            StringModel("Blood donations")
        )
        transfusionListView = TransfusionListView(listOfTransfusionMenuItem) // initializing adapter
        binding.fragmentTransactionListItemEpoxyRecyclerview.layoutManager = LinearLayoutManager(requireContext()) // adding layout manager to epoxyrecyclerview
        binding.fragmentTransactionListItemEpoxyRecyclerview.adapter = transfusionListView // Attaching adapter to the recyclerview
    }
}
