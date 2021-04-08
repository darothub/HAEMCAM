package ng.com.thewhitecellfoundation.common.utils

import com.google.android.material.textfield.TextInputEditText

/**
 * @author Abdulrasaq Durosomo
 * @param respond
 * @param edits
 */
class Validation private constructor(
    var respond: Pair<CustomEditTextField, TextInputEditText?>,
    vararg var edits: Pair<CustomEditTextField, TextInputEditText?>
) {

    class Builder(
        var emailPair: Pair<CustomEditTextField, TextInputEditText?>? = null,
        var passwordPair: Pair<CustomEditTextField, TextInputEditText?>? = null,
        var phoneNumberPair: Pair<CustomEditTextField, TextInputEditText?>? = null,
        var respond: Pair<CustomEditTextField, TextInputEditText?>? = null,
        vararg var fieldPairList: Pair<CustomEditTextField, TextInputEditText?>

    ) {

        /**
         * @param edits
         * This separates field based on their tag and also rejects empty field
         */
        fun separateFieldByTag(edits: List<Pair<CustomEditTextField, TextInputEditText?>>): Builder = apply {
            this.fieldPairList = edits.toTypedArray()
            for (edit in edits) {
                when {
                    edit.first.text.isEmpty() -> {
                        edit.first.error = "${edit.first.tag} is required"
                        edit.second?.error = "${edit.first.tag} is required"
                        this.respond = edit
                        return@apply
                    }
                    edit.first.tag.toString().contains("email", true) -> {
                        this.emailPair = edit
                    }
                    edit.first.tag.toString().contains("password", true) -> {
                        this.passwordPair = edit
                    }
                    edit.first.tag.toString().contains("phone", true) -> {
                        this.phoneNumberPair = edit
                    }
                }
            }
        }

        /**
         * Validate email address
         */
        fun email() = apply {
            val emailPattern = Regex("""^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*${'$'}""")
            val matchedEmail = emailPattern.matches(this.emailPair?.first?.text.toString())
            if (matchedEmail) {
                return@apply
            } else {
                this.respond = emailPair
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
            val matchedPassword = passwordPattern.matches(this.passwordPair?.first?.text.toString())
            if (matchedPassword) {
                return@apply
            } else {
                this.respond = passwordPair
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
            val matchedPhone = phonePattern.matches(this.phoneNumberPair?.first?.text.toString())
            if (matchedPhone) {
                return@apply
            } else {
                this.respond = phoneNumberPair
                this.respond?.first?.error = "Invalid"
                this.respond?.second?.error = "Invalid"
                return@apply
            }
        }

        /**
         * Build validation
         */
        fun build(): Validation? {
            val listOfDefaulters = arrayListOf<Pair<CustomEditTextField, TextInputEditText?>?>()
            for (edit in this.fieldPairList) {
                if (edit.first.error != "") {
                    listOfDefaulters.add(edit)
                    this.respond = listOfDefaulters[0]
                    this.respond!!.second?.error = "Invalid"
                }
            }
            return respond?.let { Validation(it, *fieldPairList) }
        }
    }
}
