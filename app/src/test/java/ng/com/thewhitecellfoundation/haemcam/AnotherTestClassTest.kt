package ng.com.thewhitecellfoundation.haemcam

import org.junit.Assert.*
import org.junit.Test

class AnotherTestClassTest {

    @Test
    fun addedMeUp() {
        val t = AnotherTestClass()
        assert(t.addedMeUp() == "Add me up")
    }
}
