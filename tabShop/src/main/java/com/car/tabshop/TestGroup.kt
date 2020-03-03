package com.car.tabshop

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.ViewGroup
import com.car.core.utils.dimen.StatusBarHeight
import com.hjq.toast.ToastUtils

/**
 * @name CarSteward
 * @class name：com.car.tabshop
 * @author 345 QQ:1831712732
 * @time 2020/2/16 22:26
 * @description
 */
class TestGroup : ViewGroup {

    private val paint = Paint()
    private val path = Path()

    var h = 0f
    var mHeight = 50f

    var bool = false

    init {
        paint.color = Color.WHITE
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAtt: Int) : super(context, attrs, defStyleAtt) {
        setWillNotDraw(false)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    private fun setHeightX(height: Float) {
        mHeight = height
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        h = height / 3.toFloat()

        if (!bool) {
            mHeight = h
            bool = true
        }


        path.reset()
        path.moveTo(0f, 0f)
        path.lineTo(100f, 0f)
        path.quadTo(200f, mHeight, 300f, 0f)

        path.lineTo(800f, h)

        canvas.drawPath(path, paint)

        canvas.clipPath(path)
    }
}