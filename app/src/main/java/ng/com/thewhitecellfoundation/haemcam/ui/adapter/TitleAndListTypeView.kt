package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.common.views.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.TitleListTypeLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.TitleAndListType

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, defaultLayout = R.layout.title_list_type_layout)
class TitleAndListTypeView@JvmOverloads constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attr, defStyleAttr) {

    var binding: TitleListTypeLayoutBinding = TitleListTypeLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    companion object {
        val tdp = hashMapOf<String, ArrayList<DataPair>>()
    }
    val list = arrayListOf<DrugDays>()

    val m = hashMapOf<String, String>()
    val a = arrayListOf<DataPair>()

    @ModelProp
    fun setData(data: TitleAndListType<DrugDays>) {
        binding.titleTv.text = data.title
    }

    @CallbackProp
    fun setOnAddListener(data: DrugDays?) {
        binding.titleTv.customOnDrawableRightListener {
            if (data != null) {
                list.add(data)
            }
            binding.listErcv.withModels {
                list.forEach { dd ->
                    val p = DataPair("", "")
                    a.add(p)
                    dd.tag?.let { tdp.put(it, a) }
                    drugDaysView {
                        Log.i("DrugsDay", "$dd id ${dd.id}")
                        id(dd.id)
                        data(dd)
                        onDeleteListener { model, parentView, clickedView, position ->
                            list.removeAt(position)
                            requestModelBuild()
                        }

                        getDrugData { _, _, _, newItem ->
                            p.first = newItem
                            Log.i("TDP", "$tdp")
                            Toast.makeText(context, "$tdp", Toast.LENGTH_SHORT).show()
                        }
                        getDaysTimeData { oldIndex, oldItem, newIndex, newItem ->
                            Log.i("TDP", "$tdp")
                            p.second = newItem
                            Toast.makeText(context, "$tdp", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

data class DataPair(var first: String, var second: String)
