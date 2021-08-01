package ng.com.thewhitecellfoundation.haemcam.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ng.com.thewhitecellfoundation.common.extensions.hide
import ng.com.thewhitecellfoundation.common.extensions.show
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.BtnPbarLayoutBinding

class ButtonProgressView@JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {

    private val binding: BtnPbarLayoutBinding = BtnPbarLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    private val ta = context.obtainStyledAttributes(attr, R.styleable.ButtonProgressView)
    var buttonBgColor = ta.getResourceId(R.styleable.ButtonProgressView_button_bg_color, R.color.primaryColor)
        set(value) = setButtonBackgroundColor(value)
    var buttonBackground = ta.getDrawable(R.styleable.ButtonProgressView_button_background)
        set(value) = setButtonBackground(value)
    var buttonText = ta.getString(R.styleable.ButtonProgressView_button_text)
        set(value) = setButtonText(value)
    var loading = ta.getBoolean(R.styleable.ButtonProgressView_loading, false)
        set(value) = setLoading(value)
    var progressBarTint = ta.getResourceId(R.styleable.ButtonProgressView_custom_indeterminate_tint, R.color.primaryColor)
        set(value) = setProgressBarTint(value)

    var enable = ta.getBoolean(R.styleable.ButtonProgressView_enabled, false)

    init {
        loadAttr()
        ta.recycle()
    }

    private fun loadAttr() {
        setLoading(loading)
        setButtonText(buttonText)
        setButtonBackground(buttonBackground)
        setButtonBackgroundColor(buttonBgColor)
        setProgressBarTint(progressBarTint)
    }

    @JvmName("setLoading1")
    private fun setLoading(loading: Boolean) {
        isClickable = !loading
        if (loading) {
            binding.btn.hide()
            binding.progressBar.show()
        } else {
            binding.btn.show()
            binding.progressBar.hide()
        }
        postInvalidate()
    }

    @JvmName("setButtonText1")
    private fun setButtonText(text: String?) {
        binding.btn.text = text
        postInvalidate()
    }
    @JvmName("setButtonBackground1")
    private fun setButtonBackground(buttonBackground: Drawable?) {
        binding.btn.background = buttonBackground
        postInvalidate()
    }
    private fun setButtonBackgroundColor(@ColorRes color: Int) {
        buttonBackground?.changeBackgroundColor(context, color)
        postInvalidate()
    }
    @JvmName("setProgressBarTint1")
    private fun setProgressBarTint(@ColorRes color: Int) {
        binding.progressBar.indeterminateTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        postInvalidate()
    }
    fun Drawable.changeBackgroundColor(context: Context, color: Int) {
        this.colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(context, color),
            PorterDuff.Mode.SRC_IN
        )
    }
}
