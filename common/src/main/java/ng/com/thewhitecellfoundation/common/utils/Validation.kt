package ng.com.thewhitecellfoundation.common.utils

import com.google.android.material.textfield.TextInputEditText

class Validation private constructor(
    var respond:CustomEditText,
    vararg var edits: Pair<CustomEditText, TextInputEditText?>
) {

    class Builder(
        var email: CustomEditText? = null,
        var password: CustomEditText? = null,
        var phoneNumber: CustomEditText? = null,
        var respond:CustomEditText?=null,
        vararg var edits: Pair<CustomEditText, TextInputEditText?>

    ) {

        fun separateFieldByTag(edits: List<Pair<CustomEditText, TextInputEditText?>>): Builder = apply {
            this.edits = edits.toTypedArray()
            for (edit in edits) {
                when {
                    edit.first.text.isEmpty() -> {
                        edit.first.error = "${edit.first.tag} is required"
                        edit.second?.error = "${edit.first.tag} is required"
                        this.respond = edit.first
                        return@apply
                    }
                    edit.first.tag.toString().contains("email", true) -> {
                        this.email = edit.first
                    }
                    edit.first.tag.toString().contains("password", true) -> {
                        this.password = edit.first
                    }
                    edit.first.tag.toString().contains("phone", true) -> {
                        this.phoneNumber = edit.first
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
            val passwordPattern = Regex("""^[a-zA-Z0-9@$!.%*#?&]{6,15}$""")
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
            val matchedPhone = phonePattern.matches(this.phoneNumber?.text.toString())
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
            val listOfDefaulters = arrayListOf<CustomEditText>()
            for (edit in this.edits){
                if (edit.first.error != ""){
                    listOfDefaulters.add(edit.first)
                    this.respond = listOfDefaulters[0]
                    this.respond!!.error = "Invalid"
                }
            }
           return respond?.let { Validation(it, *edits) }
        }
    }

}

class CustomEditText(var text:CharSequence, var tag:CharSequence, var error:CharSequence?="")