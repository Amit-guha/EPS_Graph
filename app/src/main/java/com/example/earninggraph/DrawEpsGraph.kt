package com.example.earninggraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

import java.math.RoundingMode
import java.text.DecimalFormat


class DrawEpsGraph(context: Context, attrs: AttributeSet) :
    View(context, attrs) {
    private val topSpace = dpToPx(50f, context)

    private var upToDownInsideTextSpace = 0f
    private var quarterSpace = 0f

    private var xAxisPoint = ArrayList<Float>()
    var epsGraphListItem = ArrayList<EpsModel>()
    private var calculateXAxisEqualIncreaseRate = 0f
    private var isMultiplyBy100: Boolean = false

    private val X_AXIS_INSIDE_DOT_GRID_SPACE = dpToPx(50f, context)
    private var multiplyByValue = 0f
    private val df = DecimalFormat("#.##")

    val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 2f
    }

    private val textPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.base_black_80)
        textSize = dpToPx(15f, context).toFloat()
        isAntiAlias = true
        textAlignment = TEXT_ALIGNMENT_CENTER
        typeface = ResourcesCompat.getFont(context, R.font.sf_pro_text_light)
    }

    private val latestYrCircleColor = Paint().apply {
        color = ContextCompat.getColor(context, R.color.main_color_primary_100)
        isAntiAlias = true
        paint.style = Paint.Style.FILL
    }

    private val previousYrCircleColor = Paint().apply {
        color = ContextCompat.getColor(context, R.color.previous_year_eps_graph_circle_color)
        isAntiAlias = true
    }


    fun drawChart(dataList: List<EpsModel>) {
        epsGraphListItem.clear()
        epsGraphListItem.addAll(dataList)
        xAxisPoint.clear()


        val yAxisHighestPoint =
            if ((epsGraphListItem.maxOf { it.latestEps }) >= (epsGraphListItem.maxOf { it.previousEps })) {
                epsGraphListItem.maxOf { it.latestEps }
            } else {
                epsGraphListItem.maxOf { it.previousEps }
            }

        val yAxisLowestPoint =
            if ((epsGraphListItem.minOf { it.latestEps }) <= (epsGraphListItem.minOf { it.previousEps })) {
                epsGraphListItem.minOf { it.latestEps }
            } else {
                epsGraphListItem.minOf { it.previousEps }
            }

        df.roundingMode = RoundingMode.CEILING
        var storeLowestPoint = yAxisLowestPoint

        val equallyDividePoint =
            df.format((yAxisHighestPoint - yAxisLowestPoint) / 3)
                .toFloat()

        for (i in dataList.indices - 1) {
            when (i) {
                0 -> {
                    xAxisPoint.add((String.format("%.2f", yAxisHighestPoint).toFloat()))
                }

                epsGraphListItem.lastIndex -> {
                    xAxisPoint.add(String.format("%.2f", yAxisLowestPoint).toFloat())
                }

                else -> {
                    storeLowestPoint += equallyDividePoint
                    xAxisPoint.add(String.format("%.2f", storeLowestPoint).toFloat())
                }
            }
        }

        xAxisPoint.sortDescending()
        val convertToInteger = xAxisPoint.maxOf { it }.toInt()
        val isLengthOne = convertToInteger.toString().length == 1
        if (isLengthOne) {
            isMultiplyBy100 = true
            calculateXAxisEqualIncreaseRate =
                (xAxisPoint.maxOf { it } * 100 - xAxisPoint.minOf { it } * 100) / 3
            multiplyByValue = String.format(
                "%.2f",
                X_AXIS_INSIDE_DOT_GRID_SPACE / calculateXAxisEqualIncreaseRate
            ).toFloat()
        } else {
            isMultiplyBy100 = false
            calculateXAxisEqualIncreaseRate =
                (xAxisPoint.maxOf { it } - xAxisPoint.minOf { it }) / 3
            multiplyByValue = String.format(
                "%.2f",
                X_AXIS_INSIDE_DOT_GRID_SPACE / calculateXAxisEqualIncreaseRate
            ).toFloat()
        }

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(parentWidth, 4 * X_AXIS_INSIDE_DOT_GRID_SPACE + dpToPx(70f, context))

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val canvasWidth = width
        //val canvasHeight = height

        df.roundingMode = RoundingMode.CEILING
        quarterSpace = (canvasWidth - 160f) / epsGraphListItem.size

        xAxisPoint.forEachIndexed { index, _ ->
            upToDownInsideTextSpace =
                (topSpace + index * X_AXIS_INSIDE_DOT_GRID_SPACE).toFloat()
            canvas.drawText(
                xAxisPoint[index].toString(),
                15f,
                upToDownInsideTextSpace,
                textPaint
            )
        }


        epsGraphListItem.forEachIndexed { index, _ ->
            //Previous Year Eps Value
            drawPreviousYearEps(canvas, index)

            //Latest Year Eps Value
            drawLatestYearEps(canvas, index)

            //Draw Quarter Item and Year
            drawQuarterAndYear(canvas, index)

        }
    }

    private fun drawLatestYearEps(canvas: Canvas, index: Int) {
        val differenceBetweenTwoPoint =
            String.format("%.2f", xAxisPoint.maxOf { it } - epsGraphListItem[index].latestEps)
                .toFloat()
        if (!isMultiplyBy100) {
            canvas.drawCircle(
                dpToPx(80f, context) + index * quarterSpace,
                (dpToPx(45f, context) + differenceBetweenTwoPoint * multiplyByValue),
                dpToPx(8f, context).toFloat(),
                latestYrCircleColor
            )
        } else {
            canvas.drawCircle(
                dpToPx(80f, context) + index * quarterSpace,
                (dpToPx(45f, context) + 100 * multiplyByValue * differenceBetweenTwoPoint),
                dpToPx(8f, context).toFloat(),
                latestYrCircleColor
            )
        }
    }

    private fun drawPreviousYearEps(canvas: Canvas, index: Int) {
        val differenceBetweenTwoPoint =
            String.format("%.2f", xAxisPoint.maxOf { it } - epsGraphListItem[index].previousEps)
                .toFloat()
        if (!isMultiplyBy100) {
            canvas.drawCircle(
                dpToPx(80f, context) + index * quarterSpace,
                (dpToPx(45f, context) + differenceBetweenTwoPoint * multiplyByValue),
                dpToPx(8f, context).toFloat(),
                previousYrCircleColor
            )
        } else {
            canvas.drawCircle(
                dpToPx(80f, context) + index * quarterSpace,
                (dpToPx(45f, context) + 100 * multiplyByValue * differenceBetweenTwoPoint),
                dpToPx(8f, context).toFloat(),
                previousYrCircleColor
            )
        }
    }

    private fun drawQuarterAndYear(canvas: Canvas, index: Int) {
        //canvas.drawText(epsGraphListItem[index].QuarterItem, dpToPx(70f,context) + index * quarterSpace, canvas.height.toFloat() - dpToPx(40f,context), textPaint)
        //canvas.drawText(epsGraphListItem[index].Year, dpToPx(65f,context) + index * quarterSpace, canvas.height.toFloat() - dpToPx(10f,context) , textPaint)

        canvas.drawText(
            epsGraphListItem[index].QuarterItem,
            dpToPx(70f, context) + index * quarterSpace,
            upToDownInsideTextSpace + dpToPx(40f, context),
            textPaint
        )
        canvas.drawText(
            epsGraphListItem[index].Year,
            dpToPx(65f, context) + index * quarterSpace,
            upToDownInsideTextSpace + dpToPx(65f, context),
            textPaint
        )
    }

    private fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }

}





