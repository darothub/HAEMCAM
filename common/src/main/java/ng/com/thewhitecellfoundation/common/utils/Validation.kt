package ng.com.thewhitecellfoundation.common.utils

import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

//class Validation private constructor(
//    val email: String? = null,
//    val password: String? = null,
//    val phoneNumber: String? = null,
//    var respond:Any?=null,
//    vararg edits: Array<out EditText?>
//
//) {
//    class Builder(
//        var email: String? = null,
//        var password: String? = null,
//        var phoneNumber: String? = null,
//        var respond:Any?=null,
//        vararg var edits: EditText?
//
//    ) {
//
//        fun getEmptyField(edits: Array<out TextInputEditText?>): Builder? = apply {
//            this.edits = edits
//            for (edit in edits) {
//                if (edit?.text?.isEmpty() == true) {
//                    this.respond = edit
//                    return@apply
//                }
//
//            }
//            return null
//
//        }
//        fun email(email: String) = apply {
//            this.email = email
//            val emailPattern = Regex("""^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*${'$'}""")
//            val matchedEmail = emailPattern.matches(email)
//            if(matchedEmail){
//                this.respond = matchedEmail
//                return@apply
//            }
//            else{
//                this.respond = email
//                return@apply
//            }
//        }
//        fun password(password: String) = apply {
//            this.password = password
//            val passwordPattern = Regex("""^[a-zA-Z0-9@$!.%*#?&]{6,}$""")
//            val matchedPassword = passwordPattern.matches(password)
//            if(matchedPassword){
//                this.respond = matchedPassword
//                return@apply
//            }
//            else{
//                this.respond = password
//                return@apply
//            }
//        }
//        fun phone(phoneNumber: String) = apply {
//            val phonePattern = Regex("""^(80|70|90|81)([12356709])\d{7}$""")
//            val matchedPhone = phonePattern.matches(phoneNumber)
//            if(matchedPhone){
//                this.respond = matchedPhone
//                return@apply
//            }
//            else{
//                this.respond = phoneNumber
//                return@apply
//            }
//
//        }
//        fun build() = Validation(email, password, phoneNumber, respond, edits)
//    }
//
//}

class Validation private constructor(
    var respond:TextInputEditText,
    vararg edits: TextInputEditText
) {

    class Builder(
        var email: TextInputEditText? = null,
        var password: TextInputEditText? = null,
        var phoneNumber: TextInputEditText? = null,
        var respond:TextInputEditText?=null,
        vararg var edits: TextInputEditText

    ) {

        fun getEmptyField(edits: Array<out TextInputEditText>): Builder = apply {
            this.edits = edits
            for (edit in edits) {
                when {
                    edit.text?.isEmpty() == true -> {
                        edit.error = "${edit.tag} is required"
                        this.respond = edit
                        return@apply
                    }
                    edit.tag.toString().contains("email", true) -> {
                        this.email = edit
                    }
                    edit.tag.toString().contains("password", true) -> {
                        this.password = edit
                    }
                    edit.tag.toString().contains("phone", true) -> {
                        this.phoneNumber = edit
                    }
                }

            }
        }
        fun email() = apply {
            val emailPattern = Regex("""^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*${'$'}""")
            val matchedEmail = emailPattern.matches(this.email?.text.toString())
            if(matchedEmail){
                return@apply
            }
            else{
                this.respond = email
                this.respond?.error = "Invalid"
                return@apply
            }
        }
        fun password() = apply {
            val passwordPattern = Regex("""^[a-zA-Z0-9@$!.%*#?&]{6,}$""")
            val matchedPassword = passwordPattern.matches(this.password?.text.toString())
            if(matchedPassword){
                return@apply
            }
            else{
                this.respond = password
                this.respond?.error = "Invalid"
                return@apply
            }
        }
        fun phone() = apply {
            val phonePattern = Regex("""^(80|70|90|81)([12356709])\d{7}$""")
            val matchedPhone = phonePattern.matches(this.phoneNumber.toString())
            if(matchedPhone){
                return@apply
            }
            else{
                this.respond = phoneNumber
                this.respond?.error = "Invalid"
                return@apply
            }

        }
        fun build():Validation? {
            val listOfDefaulters = arrayListOf<TextInputEditText>()
            for (edit in this.edits){
                if (edit.error != null){
                    listOfDefaulters.add(edit)
                    this.respond = listOfDefaulters[0]
                    this.respond!!.error = "Invalid"
                }
            }
           return respond?.let { Validation(it, *edits) }
        }
    }

}