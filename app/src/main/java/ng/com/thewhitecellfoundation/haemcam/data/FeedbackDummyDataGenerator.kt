package ng.com.thewhitecellfoundation.haemcam.data

import ng.com.thewhitecellfoundation.haemcam.model.FeedBack

/**
 * Generates a dummy [FeedBack] data for the feedback fragment recyclerview
 */
object FeedbackDummyDataGenerator {
    fun createFeedbackData(): ArrayList<FeedBack> {

        return arrayListOf(
            FeedBack(
                "Dr Anthonia Peterson",
                "Here are some medical feedback Here are some medical feedback Here are some medical feedback Here are some medical feedbackHere are some medical feedbackHere are some medical feedback",
                23,
                listOf("I love this", "No I do not")
            ),
            FeedBack(
                "Dr Anthonia Peterson",
                "Here are some medical feedback Here are some medical feedback Here are some medical feedback Here are some medical feedbackHere are some medical feedbackHere are some medical feedback",
                23,
                listOf("I love this", "No I do not")
            ),
            FeedBack(
                "Dr Anthonia Peterson",
                "Here are some medical feedback Here are some medical feedback Here are some medical feedback Here are some medical feedbackHere are some medical feedbackHere are some medical feedback",
                23,
                listOf("I love this", "No I do not")
            ),

        )
    }
}
