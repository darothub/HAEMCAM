package ng.com.thewhitecellfoundation.haemcam.model

import java.util.concurrent.atomic.AtomicInteger

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
        var atomicId = AtomicInteger(0)
    }

    init {
        id = idPlaceholder++
    }
}
