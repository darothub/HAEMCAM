package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.common.views.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.TitleListTypeLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.TitleAndListType

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.title_list_type_layout
)
class TitleAndListTypeView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attr, defStyleAttr) {

    var binding: TitleListTypeLayoutBinding = TitleListTypeLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    companion object {
        val tdp = hashMapOf<String, ArrayList<DataPair>>()
        var num = 1.toLong()
    }

    private val list = arrayListOf<DrugDays>()

    private val listOfDataPair = arrayListOf<DataPair>()

    @ModelProp
    fun setData(data: TitleAndListType<DrugDays>) {
        binding.titleTv.text = data.title
        binding.listErcv.withModels {
            data.list?.forEach { dd ->
                drugDaysView {
                    id(dd.id)
                    data(dd)
                }
            }
        }
    }

    @CallbackProp
    fun setOnAddListener(data: DrugDays?) {
        binding.titleTv.customOnDrawableRightListener {
            if (data != null) {
                list.add(data)
            }
            binding.listErcv.withModels {
                list.forEach { dd ->
                    val n = dd.copy(dataPair = DataPair("", ""))
                    drugDaysView {
                        Log.i("DrugsDay", "$dd id ${n.id}")
                        id(dd.id)
                        data(dd)
                        onDeleteListener { model, parentView, clickedView, position ->
                            n.dataPair?.first = ""
                            n.dataPair?.second = ""
                            list.removeAt(position)
                            requestModelBuild()
                        }

                        getDrugData { _, _, _, newItem ->
                            n.dataPair?.first = newItem
                            Log.i("TDP", "$n")
                            Toast.makeText(context, "$n", Toast.LENGTH_SHORT).show()
                        }
                        getDaysTimeData { _, _, _, newItem ->
                            n.dataPair?.second = newItem
                            Log.i("TDP", "$n")
                            Toast.makeText(context, "$n", Toast.LENGTH_SHORT).show()
                        }
                        n.dataPair?.let { listOfDataPair.add(it) }
                        dd.tag?.let { tdp[it] = listOfDataPair }
                    }
                }
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        tdp.clear()
    }
}
