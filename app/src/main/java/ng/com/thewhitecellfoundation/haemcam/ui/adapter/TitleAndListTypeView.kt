package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
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
    val list = arrayListOf<DrugDays>()
    @ModelProp
    fun setData(data: TitleAndListType<DrugDays>) {
        binding.titleTv.text = data.title
//        data.list?.let { list.addAll(it) }
//        binding.listErcv.withModels {
//            list?.forEach { dd ->
//                dd.hint = data.hint
//                drugDaysView {
//                    id(dd.id)
//                    data(dd)
//                }
//            }
//        }
    }

    @CallbackProp
    fun setOnAddListener(data: DrugDays?) {
        binding.titleTv.customOnDrawableRightListener {
            if (data != null) {
                list.add(data)
            }
            binding.listErcv.withModels {
                list?.forEach { dd ->
                    dd.hint = data?.hint
                    drugDaysView {
                        Log.i("DrugsDay", "$dd id ${dd.id}")
                        id(dd.id)
                        data(dd)
                        onDeleteListener { model, parentView, clickedView, position ->
                            list.removeAt(position)
                            requestModelBuild()
                        }
                    }
                }
            }
        }
    }
}
