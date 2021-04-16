package ng.com.thewhitecellfoundation.haemcam.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.concurrent.atomic.AtomicInteger

data class HomeItemData(@DrawableRes val image: Int, @StringRes val title: Int) {
    var id: Long = 0

    companion object {
        var atomicId = AtomicInteger(0)
    }

    init {
        id = atomicId.incrementAndGet().toLong()
    }
}
