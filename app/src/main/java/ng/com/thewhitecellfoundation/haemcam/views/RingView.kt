package ng.com.thewhitecellfoundation.haemcam.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import ng.com.thewhitecellfoundation.haemcam.R
import kotlin.math.min

class RingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val ta = context.obtainStyledAttributes(attrs, R.styleable.RingView)
    private val outerColor = ta.getColor(R.styleable.RingView_outer_color, Color.BLACK)
    private val innerColor = ta.getColor(R.styleable.RingView_inner_color, Color.WHITE)
    private val circleSize = ta.getDimensionPixelSize(R.styleable.RingView_size, 100)
    private val outerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val innerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private var outerRadius = 0.0f // Radius of the circle.
    private var innerRadius = 0.0f
    // position variable which will be used to draw label and indicator circle position
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    init {
        outerPaint.color = outerColor
        innerPaint.color = innerColor
        ta.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), outerRadius, outerPaint)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), innerRadius, innerPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = circleSize + paddingLeft + paddingRight
        val desiredHeight = circleSize + paddingTop + paddingBottom
        setMeasuredDimension(
            measureDimension(desiredWidth, widthMeasureSpec),
            measureDimension(desiredHeight, heightMeasureSpec)
        )
    }
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        outerRadius = (min(width, height) / 2 * 0.8).toFloat()
        innerRadius = (min(width, height) / 3 * 0.8).toFloat()
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        if (result < desiredSize) {
            Log.e("ChartView", "The view is too small, the content might get cut")
        }
        return result
    }
}
