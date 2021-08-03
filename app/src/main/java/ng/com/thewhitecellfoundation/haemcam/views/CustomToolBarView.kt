package ng.com.thewhitecellfoundation.haemcam.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toolbar
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import coil.load
import ng.com.thewhitecellfoundation.common.extensions.hide
import ng.com.thewhitecellfoundation.common.extensions.show
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ReusableToolbarBinding

class CustomToolBarView@JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attr, defStyleAttr) {

    private val binding: ReusableToolbarBinding = ReusableToolbarBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    private val ta = context.obtainStyledAttributes(attr, R.styleable.CustomToolBarView)
    var toolBarTitle = ta.getString(R.styleable.CustomToolBarView_title)
        set(value) = setToolBarTitle(value)
    var customBgColor = ta.getResourceId(R.styleable.CustomToolBarView_custom_bg_color, R.color.white)
        set(value) = setToolBarBgColor(value)
    var leftImage = ta.getDrawable(R.styleable.CustomToolBarView_left_icon)
        set(value) = setLeftImage(value)
    var leftImageUrl = ""
        set(value) = setLeftImage(value)
    var rightImage = ta.getDrawable(R.styleable.CustomToolBarView_right_icon)
        set(value) = setRightImage(value)
    var rightImageUrl = ""
        set(value) = setRightImage(value)
    var greetings = ta.getBoolean(R.styleable.CustomToolBarView_greetings, false)
        set(value) = setGreetings(value)
    var greetingText = ta.getString(R.styleable.CustomToolBarView_greeting_text)
        set(value) = setGreetingsText(value)

    init {
        loadAttr()
        ta.recycle()
    }

    private fun loadAttr() {
        setLeftImage(leftImage)
        setRightImage(rightImage)
        setToolBarBgColor(customBgColor)
        setGreetingsText(greetingText)
        setToolBarTitle(toolBarTitle)
    }

    @JvmName("setLeftImage1")
    private fun setLeftImage(icon: Drawable?) {
        binding.leftImage.setImageDrawable(icon)
        postInvalidate()
    }
    @JvmName("setLeftIcon1")
    private fun setLeftImage(iconUrl: String?) {
        binding.leftImage.load(iconUrl)
        postInvalidate()
    }
    @JvmName("setRightImage1")
    private fun setRightImage(icon: Drawable?) {
        binding.rightImage.setImageDrawable(icon)
        postInvalidate()
    }
    private fun setRightImage(iconUrl: String?) {
        binding.rightImage.load(iconUrl)
        postInvalidate()
    }
    @JvmName("setGreetings1")
    private fun setGreetings(loading: Boolean) {
        postInvalidate()
    }

    private fun setGreetingsText(text: String?) {

        if (text == "null" || text == null) {
            binding.userGreetingsTv.hide()
        } else {
            binding.userGreetingsTv.show()
            binding.userGreetingsTv.text = text
        }

        postInvalidate()
    }
    @JvmName("setToolBarTitle1")
    private fun setToolBarTitle(text: String?) {
        if (text == "null" || text == null) {
            binding.titleTv.hide()
        } else {
            binding.titleTv.show()
            binding.titleTv.text = text
        }

        postInvalidate()
    }
    fun setToolBarBgColor(@ColorRes color: Int) {
        binding.root.setBackgroundColor(ContextCompat.getColor(context, color))
        postInvalidate()
    }
    fun leftImageClickListener(onclick: (() -> Unit?)) {
        binding.leftImage.setOnClickListener {
            onclick.invoke()
        }
    }
    fun rightImageClickListener(onclick: (() -> Unit?)) {
        binding.rightImage.setOnClickListener {
            onclick.invoke()
        }
    }
}
