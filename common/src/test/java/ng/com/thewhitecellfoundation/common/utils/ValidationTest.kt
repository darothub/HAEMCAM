package ng.com.thewhitecellfoundation.common.utils

import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidationTest {

    @Test
    fun `test for empty email field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("", "email", ""), null),
            Pair(CustomEditText("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .build()

        res?.respond?.tag?.contains("email", true)?.let { assert(it) }
    }
    @Test
    fun `test for empty password field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("hello@gmail.com", "email", ""), null),
            Pair(CustomEditText("", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .build()

        res?.respond?.tag?.contains("password", true)?.let { assert(it) }
    }
    @Test
    fun `test for empty phone number field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("", "phone", ""), null),
            Pair(CustomEditText("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .build()

        res?.respond?.tag?.contains("phone", true)?.let { assert(it) }
    }

    @Test
    fun `test for invalid email field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("hello", "email", ""), null),
            Pair(CustomEditText("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .email()
            .build()

        assert(res?.respond == listOfCustomEditText[0].first)
    }

    @Test
    fun `test for invalid password field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("hello@gmail.com", "email", ""), null),
            Pair(CustomEditText("pass", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .password()
            .build()

        assert(res?.respond == listOfCustomEditText[1].first)
    }

    @Test
    fun `test for invalid phone field`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("080600981627", "phone", ""), null),
            Pair(CustomEditText("P", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .phone()
            .build()

        assert(res?.respond == listOfCustomEditText[0].first)
    }

    @Test
    fun `test for correct email input`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("hello@gmail.com", "email", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .email()
            .build()

        assert(res?.respond == null)


    }
    @Test
    fun `test for correct password input`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("Password", "password", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .password()
            .build()

        assert(res?.respond == null)
    }

    @Test
    fun `test for correct phone input`() {
        val listOfCustomEditText = listOf(
            Pair(CustomEditText("8060085192", "phone", ""), null)
        )
        val res = Validation.Builder()
            .separateFieldByTag(listOfCustomEditText)
            .phone()
            .build()

        assert(res?.respond == null)
    }
}
