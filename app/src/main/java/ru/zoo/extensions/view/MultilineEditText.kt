package ru.zoo.extensions.view

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo

import android.view.inputmethod.InputConnection

import android.widget.EditText
import android.widget.TextView


class MultilineEditText : androidx.appcompat.widget.AppCompatEditText {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        val connection = super.onCreateInputConnection(outAttrs)
        val imeActions = outAttrs.imeOptions and EditorInfo.IME_MASK_ACTION
        if (imeActions and EditorInfo.IME_ACTION_DONE != 0) {
            outAttrs.imeOptions = outAttrs.imeOptions xor imeActions
            outAttrs.imeOptions = outAttrs.imeOptions or EditorInfo.IME_ACTION_DONE
        }
        if (outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION != 0) {
            outAttrs.imeOptions = outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION.inv()
        }
        return connection
    }
}
fun editTextTitleIsShow(textView: TextView,valueText:String){
    textView.toggleVisibility(valueText != "")
}