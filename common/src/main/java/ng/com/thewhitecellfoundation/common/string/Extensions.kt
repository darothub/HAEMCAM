package ng.com.thewhitecellfoundation.common.string

import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

fun TextView.setPartialSpan(vararg links: Pair<String, () -> Unit>) {
    val spannableString = SpannableString(this.text)
    links.forEach {
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                it.second.invoke()
            }
            // Change color and remove underline
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = this@setPartialSpan.textColors.defaultColor
                ds.isUnderlineText = true
            }
        }
        val text = this.text.toString()
        if (text.contains(it.first)) {
            val textIndex = text.indexOf(it.first)
            spannableString.apply {
                setSpan(
                    clickableSpan,
                    textIndex,
                    textIndex + it.first.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                    textIndex,
                    textIndex + it.first.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            this.movementMethod =
                LinkMovementMethod.getInstance()
            this.setText(spannableString, TextView.BufferType.SPANNABLE)
        }

    }
}

