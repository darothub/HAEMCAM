package ng.com.thewhitecellfoundation.haemcam.model

data class TitleAndListType<T>(
    var title: String,
    val list: ArrayList<T>? = null,
    val hint: String? = null
) : Models()
