package com.github.herokotlin.numberindicator

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.github.herokotlin.numberindicator.databinding.NumberIndicatorBinding

class NumberIndicator : LinearLayout {

    companion object {

        var DEFAULT_INDEX = 0

        var DEFAULT_COUNT = 10

        var DEFAULT_GAP = 10

        var DEFAULT_TEXT_COLOR = Color.parseColor("#BBFFFFFF")

        var DEFAULT_TEXT_SIZE = 14

    }

    private lateinit var binding: NumberIndicatorBinding

    var index = -1

        set(value) {
            if (field == value) {
                return
            }
            field = value
            binding.indexView.text = (value + 1).toString()
        }

    var count = -1

        set(value) {
            if (field == value) {
                return
            }
            field = value
            binding.countView.text = value.toString()
        }

    var separator = ""

        set(value) {
            if (field == value) {
                return
            }
            field = value
            binding.separatorView.text = value
        }

    var gap = 0

        set(value) {
            if (field == value) {
                return
            }
            field = value
            binding.separatorView.setPadding(value, 0, value, 0)
        }

    var textColor = 0

        set(value) {
            if (field == value) {
                return
            }
            field = value
            binding.indexView.setTextColor(value)
            binding.countView.setTextColor(value)
            binding.separatorView.setTextColor(value)
        }

    var textSize = 0

        set(value) {
            if (field == value) {
                return
            }
            field = value
            binding.indexView.textSize = value.toFloat()
            binding.countView.textSize = value.toFloat()
            binding.separatorView.textSize = value.toFloat()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        binding = NumberIndicatorBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.NumberIndicator, defStyle, 0)

        index = typedArray.getInt(R.styleable.NumberIndicator_number_indicator_index, DEFAULT_INDEX)

        count = typedArray.getInt(R.styleable.NumberIndicator_number_indicator_count, DEFAULT_COUNT)

        separator = typedArray.getString(R.styleable.NumberIndicator_number_indicator_separator) ?: ""

        gap = typedArray.getDimensionPixelSize(
            R.styleable.NumberIndicator_number_indicator_gap,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_GAP.toFloat(), resources.displayMetrics).toInt()
        )

        textColor = typedArray.getColor(R.styleable.NumberIndicator_number_indicator_text_color, DEFAULT_TEXT_COLOR)

        textSize = typedArray.getDimensionPixelSize(
            R.styleable.NumberIndicator_number_indicator_text_size,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE.toFloat(), resources.displayMetrics).toInt()
        )

        // 获取完 TypedArray 的值后，
        // 一般要调用 recycle 方法来避免重新创建的时候出错
        typedArray.recycle()

    }

}
