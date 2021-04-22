package ng.com.thewhitecellfoundation.haemcam.model

data class DrugDays(
    override var tag: String? = null,
    override var drug: Int? = null,
    override val days: Int? = null,
    override var hint: String? = null,
    override var dataPair: DataPair? = null
) : DrugDaysBase by DD {
    override var id: Long = 0

    companion object {
        var idPlaceholder = 0.toLong()
    }

    init {
        id = idPlaceholder++
    }
}

data class OtherDrugDays(
    override var tag: String? = null,
    override var drug: Int? = null,
    override val days: Int? = null,
    override var hint: String? = null,
    override var dataPair: DataPair? = null
) : DrugDaysBase by DD {
    override var id: Long = 0

    companion object {
        var idPlaceholder = 0.toLong()
    }

    init {
        id = idPlaceholder++
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

object DD : DrugDaysBase {
    override var id: Long = 0
    override var tag: String? = null
    override var drug: Int? = null
    override val days: Int? = null
    override var hint: String? = null
    override var dataPair: DataPair? = null
}
