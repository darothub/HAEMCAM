package ng.com.thewhitecellfoundation.haemcam.model

import java.util.concurrent.atomic.AtomicInteger

abstract class Models {
    var id: Long = 0

    companion object {
        var atomicId = AtomicInteger(0)
    }

    init {
        id = atomicId.incrementAndGet().toLong()
    }
}
