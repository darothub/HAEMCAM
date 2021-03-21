package ng.com.thewhitecellfoundation.haemcam

import org.junit.Test

import org.junit.Assert.*

class TestClassTest {
    @Test
    fun addMeUp() {
        val t = TestClass()
        assert(t.addMeUp() == "Add me up")
    }
}