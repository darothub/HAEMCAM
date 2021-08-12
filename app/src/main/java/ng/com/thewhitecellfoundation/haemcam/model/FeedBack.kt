package ng.com.thewhitecellfoundation.haemcam.model

import java.util.concurrent.atomic.AtomicInteger

data class FeedBack(
    var senderName: String,
    var body: String,
    var likes: Int,
    var comment: List<String>
) {
    var id: Long = 0

    companion object {
        var atomicId = AtomicInteger(0)
    }

    init {
        id = atomicId.incrementAndGet().toLong()
    }
}
