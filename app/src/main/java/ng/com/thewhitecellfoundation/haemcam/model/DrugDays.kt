package ng.com.thewhitecellfoundation.haemcam.model

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

data class DrugDays(
    @StringRes override var tag: Int? = null,
    @ArrayRes override var drug: Int? = null,
    @ArrayRes override val days: Int? = null,
    @StringRes override var hint: Int? = null,
    override var dataPair: DataPair? = null,
    override var id: Long = 0,
) : DrugDaysBase {

    companion object {
        var idPlaceholder = 0.toLong()
        var listOfId = arrayListOf<Long>()
        var listOfChemoTherapy = arrayListOf<DrugDays>()
    }

    init {
        id = ++idPlaceholder
        if (listOfId.isNotEmpty()) {
            listOfId.sort()
            id = listOfId[0]
            listOfId.removeAt(0)
        }
        listOfChemoTherapy.add(this)
    }

    val remove: Unit
        get() {
            val index = listOfChemoTherapy.indexOf(this)
            listOfChemoTherapy.removeAt(index)
            idPlaceholder -= 1
            listOfId.add(this.id)
        }
}

data class OtherDrugDays(
    @StringRes override var tag: Int? = null,
    @ArrayRes override var drug: Int? = null,
    @ArrayRes override val days: Int? = null,
    @StringRes override var hint: Int? = null,
    override var dataPair: DataPair? = null,
    override var id: Long = 0
) : DrugDaysBase {

    companion object {
        var idPlaceholder = 0.toLong()
        var listOfId = arrayListOf<Long>()
        var listOfOtherDrugs = arrayListOf<OtherDrugDays>()
    }

    init {
        id = ++idPlaceholder
        if (listOfId.isNotEmpty()) {
            listOfId.sort()
            id = listOfId[0]
            listOfId.removeAt(0)
        }
        listOfOtherDrugs.add(this)
    }

    val remove: Unit
        get() {
            val index = listOfOtherDrugs.indexOf(this)
            listOfOtherDrugs.removeAt(index)
            idPlaceholder -= 1
            listOfId.add(this.id)
        }
}

interface DrugDaysBase {
    var id: Long
    var tag: Int?
    var drug: Int?
    val days: Int?
    var hint: Int?
    var dataPair: DataPair?
}
//
// object DD : DrugDaysBase {
//    override var id: Long = 0
//    override var tag: String? = null
//    override var drug: Int? = null
//    override val days: Int? = null
//    override var hint: String? = null
//    override var dataPair: DataPair? = null
// }
// object DDTwo : DrugDaysBase {
//    override var id: Long = 0
//    override var tag: String? = null
//    override var drug: Int? = null
//    override val days: Int? = null
//    override var hint: String? = null
//    override var dataPair: DataPair? = null
// }
