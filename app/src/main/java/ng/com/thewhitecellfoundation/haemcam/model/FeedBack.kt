package ng.com.thewhitecellfoundation.haemcam.model

data class FeedBack(
    var senderName: String,
    var body: String,
    var likes: Int,
    var comment: List<String>
) : Models()
