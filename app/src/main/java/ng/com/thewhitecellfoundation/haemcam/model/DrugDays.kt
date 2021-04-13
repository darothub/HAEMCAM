package ng.com.thewhitecellfoundation.haemcam.model

import java.util.concurrent.atomic.AtomicInteger

data class DrugDays(
    val drug: ArrayList<String>? = null,
    val days: ArrayList<String>? = null,
    var hint: String? = null
) {
    var id: Long = 0
    companion object {
        var atomicId = AtomicInteger(0)
    }

    init {
        id = atomicId.incrementAndGet().toLong()
    }
}
