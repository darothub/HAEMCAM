package ng.com.thewhitecellfoundation.haemcam

import org.junit.Assert.*
import org.junit.Test

class TestClassTest {
    @Test
    fun addMeUp() {
        val t = TestClass()
        assert(t.addMeUp() == "Add me up")
    }
}
