package com.nikita.codeedittext_view

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.view.children


class CodeEditText : FrameLayout {

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    var maxInput: Int = 6
        set(value) {
            field = value
            //postInvalidate()
        }

    @ColorInt
    var color: Int = Color.parseColor("#ffcb32")
        set(value) {
            field = value
            //postInvalidate()
            reChangeBoxesColors()
        }

    @ColorInt
    var textColor: Int = color
        set(value) {
            field = value
            //postInvalidate()
            reChangeBoxesColors()
        }

    var onListener: (success: Boolean) -> Unit = {

    }

    var text: String = ""
        set(value) {
            field = value
            editText?.setText(value)
        }
        get() = editText?.text.toString()

    private var editText: EditText? = null
    private var boxesLayout: LinearLayout? = null

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.CodeEditText, defStyle, 0
        )

        maxInput = a.getInt(R.styleable.CodeEditText_maxInput, maxInput)
        color = a.getColor(R.styleable.CodeEditText_color, color)
        textColor = a.getColor(R.styleable.CodeEditText_textColor, textColor)

        postInvalidate()

    }

    override fun postInvalidate() {
        super.postInvalidate()
        editText = EditText(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            textSize = 0f
            background = null
            isCursorVisible = false
            filters = arrayOf<InputFilter>(LengthFilter(maxInput))
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
        }
        boxesLayout = LinearLayout(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            orientation = LinearLayout.HORIZONTAL
        }

        createBoxex()

        editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                onListener.invoke(s.length == maxInput)

                var i = 0
                for (view in boxesLayout!!.children) {
                    val txtBox = view as TextView
                    if (i > s.lastIndex) {
                        txtBox.text = ""
                    } else {
                        txtBox.text = s[i].toString()
                    }
                    i++
                }
            }
        })

        addView(boxesLayout)
        addView(editText)
    }

    private fun reChangeBoxesColors() {
        if (boxesLayout == null)
            return
        for (view in boxesLayout!!.children) {
            val txtBox = view as TextView
            txtBox.setTextColor(textColor)
            txtBox.background = textBg()
        }
    }

    private fun createBoxex() {
        boxesLayout?.removeAllViews()
        repeat(maxInput) {
            boxesLayout?.addView(createNumBox())
        }
    }

    private fun createNumBox(): TextView {
        return TextView(context).apply {
            textSize = 23f
            //setPadding(11.dp, 11.dp, 11.dp, 11.dp)
            gravity = Gravity.CENTER
            setTextColor(this@CodeEditText.textColor)
            background = textBg()
            layoutParams = LayoutParams(43.dp, 47.dp).apply {
                marginEnd = 5.dp
                marginStart = 5.dp
            }
        }
    }

    private fun textBg(): Drawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setStroke(2.dp, this@CodeEditText.color)
            cornerRadius = 10.dp.toFloat()
        }
    }

}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
