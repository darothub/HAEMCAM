package ng.com.thewhitecellfoundation.haemcam.model

data class DrugDays(
    override var tag: String? = null,
    override var drug: Int? = null,
    override val days: Int? = null,
    override var hint: String? = null,
    override var dataPair: DataPair? = null,
    override var id: Long = 0,
) : DrugDaysBase {

    companion object {
        var idPlaceholder = 0.toLong()
        var listOfId = arrayListOf<Long>()
        var listOfInstances = arrayListOf<DrugDays>()
    }

    init {
        id = ++idPlaceholder
        if (listOfId.isNotEmpty()) {
            listOfId.sort()
            id = listOfId[0]
            listOfId.removeAt(0)
        }
        listOfInstances.add(this)
    }

    fun finalize() {
        val index = listOfInstances.indexOf(this)
        listOfInstances.removeAt(index)
        idPlaceholder -= 1
        listOfId.add(this.id)
    }
}

data class OtherDrugDays(
    override var tag: String? = null,
    override var drug: Int? = null,
    override val days: Int? = null,
    override var hint: String? = null,
    override var dataPair: DataPair? = null,
    override var id: Long = 0
) : DrugDaysBase {

    companion object {
        var idPlaceholder = 0.toLong()
        var listOfId = arrayListOf<Long>()
        var listOfInstances = arrayListOf<OtherDrugDays>()
    }

    init {
        id = ++idPlaceholder
        if (listOfId.isNotEmpty()) {
            listOfId.sort()
            id = listOfId[0]
            listOfId.removeAt(0)
        }
        listOfInstances.add(this)
    }
    fun finalize() {
        val index = listOfInstances.indexOf(this)
        listOfInstances.removeAt(index)
        idPlaceholder -= 1
        listOfId.add(this.id)
    }
}

interface DrugDaysBase {
    var id: Long
    var tag: String?
    var drug: Int?
    val days: Int?
    var hint: String?
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
