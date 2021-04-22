package ng.com.thewhitecellfoundation.haemcam.model

data class DrugDays(
    var tag: String? = null,
    var drug: Int? = null,
    val days: Int? = null,
    var hint: String? = null,
    var dataPair: DataPair? = null
) {
    var id: Long = 0

    companion object {
        var idPlaceholder = 0.toLong()
    }

    init {
        id = idPlaceholder++
    }
}

data class OtherDrugDays(
    var tag: String? = null,
    var drug: Int? = null,
    val days: Int? = null,
    var hint: String? = null,
    var dataPair: DataPair? = null
) {
    var id: Long = 0

    companion object {
        var idPlaceholder = 0.toLong()
    }

    init {
        id = idPlaceholder++
    }
}

// interface DrugDaysBase {
//    var id: Long
//    var tag: String?
//    var drug: Int?
//    val days: Int?
//    var hint: String?
//    var dataPair: DataPair?
// }
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
