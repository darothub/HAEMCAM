package ng.com.thewhitecellfoundation.common.utils

import com.google.android.material.textfield.TextInputEditText

/**
 * @author Abdulrasaq Durosomo
 * @param respond
 * @param edits
 */
class Validation private constructor(
    var respond:Pair<CustomEditText, TextInputEditText?>,
    vararg var edits: Pair<CustomEditText, TextInputEditText?>
) {

    class Builder(
        var email: Pair<CustomEditText, TextInputEditText?>? = null,
        var password: Pair<CustomEditText, TextInputEditText?>? = null,
        var phoneNumber: Pair<CustomEditText, TextInputEditText?>? = null,
        var respond:Pair<CustomEditText, TextInputEditText?>?=null,
        vararg var edits: Pair<CustomEditText, TextInputEditText?>

    ) {

        /**
         * @param edits
         * This separates field based on their tag and also rejects empty field
         */
        fun separateFieldByTag(edits: List<Pair<CustomEditText, TextInputEditText?>>): Builder = apply {
            this.edits = edits.toTypedArray()
            for (edit in edits) {
                when {
                    edit.first.text.isEmpty() -> {
                        edit.first.error = "${edit.first.tag} is required"
                        edit.second?.error = "${edit.first.tag} is required"
                        this.respond = edit
                        return@apply
                    }
                    edit.first.tag.toString().contains("email", true) -> {
                        this.email = edit
                    }
                    edit.first.tag.toString().contains("password", true) -> {
                        this.password = edit
                    }
                    edit.first.tag.toString().contains("phone", true) -> {
                        this.phoneNumber = edit
                    }
                }

            }
        }

        /**
         * Validate email address
         */
        fun email() = apply {
            val emailPattern = Regex("""^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*${'$'}""")
            val matchedEmail = emailPattern.matches(this.email?.first?.text.toString())
            if(matchedEmail){
                return@apply
            }
            else{
                this.respond = email
                this.respond?.first?.error = "Invalid"
                this.respond?.second?.error = "Invalid"
                return@apply
            }
        }

        /**
         * Validate password
         */
        fun password() = apply {
            val passwordPattern = Regex("""^[a-zA-Z0-9@$!.%*#?&]{6,15}$""")
            val matchedPassword = passwordPattern.matches(this.password?.first?.text.toString())
            if(matchedPassword){
                return@apply
            }
            else{
                this.respond = password
                this.respond?.first?.error = "Invalid"
                this.respond?.second?.error = "Invalid"
                return@apply
            }
        }

        /**
         * Validate phone number
         */
        fun phone() = apply {
            val phonePattern = Regex("""^(80|70|90|81)([12356709])\d{7}$""")
            val matchedPhone = phonePattern.matches(this.phoneNumber?.first?.text.toString())
            if(matchedPhone){
                return@apply
            }
            else{
                this.respond = phoneNumber
                this.respond?.first?.error = "Invalid"
                this.respond?.second?.error = "Invalid"
                return@apply
            }

        }

        /**
         * Build validation
         */
        fun build():Validation? {
            val listOfDefaulters = arrayListOf<Pair<CustomEditText, TextInputEditText?>?>()
            for (edit in this.edits){
                if (edit.first.error != ""){
                    listOfDefaulters.add(edit)
                    this.respond = listOfDefaulters[0]
                    this.respond!!.second?.error = "Invalid"
                }
            }
           return respond?.let { Validation(it, *edits) }
        }
    }

}

