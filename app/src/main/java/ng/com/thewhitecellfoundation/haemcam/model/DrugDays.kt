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
