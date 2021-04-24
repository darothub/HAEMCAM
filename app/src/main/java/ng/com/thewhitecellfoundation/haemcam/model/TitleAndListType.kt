package ng.com.thewhitecellfoundation.haemcam.model

import java.util.concurrent.atomic.AtomicInteger

data class TitleAndListType<T>(
    var title: String,
    val list: ArrayList<T>? = null,
    val hint: String? = null
) {
    var id: Long = 0
    companion object {
        var atomicId = AtomicInteger(0)
    }

    init {
        id = atomicId.incrementAndGet().toLong()
    }
}
