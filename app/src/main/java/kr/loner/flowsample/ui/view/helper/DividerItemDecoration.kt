package kr.loner.flowsample.ui.view.helper

import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class DividerItemDecoration(private val mHeight: Float, color: Int) :
    ItemDecoration() {
    private val mPaint = Paint().apply {
        setColor(color)
    }

    override fun onDrawOver(a_canvas: Canvas, a_parent: RecyclerView, a_state: RecyclerView.State) {
        super.onDrawOver(a_canvas, a_parent, a_state)
        val left = a_parent.paddingLeft
        val right = a_parent.width - a_parent.paddingRight
        val childCount = a_parent.childCount
        for (i in 0 until childCount) {
            val child: View = a_parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + mHeight
            a_canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom, mPaint)
        }
    }

}