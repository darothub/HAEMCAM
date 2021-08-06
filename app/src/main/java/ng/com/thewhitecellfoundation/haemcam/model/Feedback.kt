package ng.com.thewhitecellfoundation.haemcam.model

data class Feedback(
    var id: Long = 0,
    val name: String,
    val message: String,
    var likeAction: Int,
    val likeCount: Int,
    val likeText: String,
    val commentAction: Int,
    val commentCount: Int,
    val commentText: String
) {
    companion object {
        var Id = 0.toLong()
    }

    init {
        id = ++Id
    }
}
