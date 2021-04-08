package ng.com.thewhitecellfoundation.common.utils

import org.junit.Test

class ValidationTest {

    @Test
    fun `test for empty email field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("", "email", ""), null),
            Pair(CustomEditTextField("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .build()

        res?.respond?.first?.tag?.contains("email", true)?.let { assert(it) }
    }
    @Test
    fun `test for empty password field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("hello@gmail.com", "email", ""), null),
            Pair(CustomEditTextField("", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .build()

        res?.respond?.first?.tag?.contains("password", true)?.let { assert(it) }
    }
    @Test
    fun `test for empty phone number field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("", "phone", ""), null),
            Pair(CustomEditTextField("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .build()

        res?.respond?.first?.tag?.contains("phone", true)?.let { assert(it) }
    }

    @Test
    fun `test for invalid email field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("hello", "email", ""), null),
            Pair(CustomEditTextField("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .email()
            .build()

        assert(res?.respond?.first == listOfCustomEditText[0].first)
    }

    @Test
    fun `test for invalid password field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("hello@gmail.com", "email", ""), null),
            Pair(CustomEditTextField("pass", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .password()
            .build()

        assert(res?.respond?.first == listOfCustomEditText[1].first)
    }

    @Test
    fun `test for invalid phone field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("080600981627", "phone", ""), null),
            Pair(CustomEditTextField("P", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .phone()
            .build()

        assert(res?.respond?.first == listOfCustomEditText[0].first)
    }

    @Test
    fun `test for correct email input`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("hello@gmail.com", "email", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .email()
            .build()

        assert(res?.respond?.first == null)
    }
    @Test
    fun `test for correct password input`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .password()
            .build()

        assert(res?.respond?.first == null)
    }

    @Test
    fun `test for correct phone input`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditTextField("8060085192", "phone", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .phone()
            .build()

        assert(res?.respond?.first == null)
    }
}
