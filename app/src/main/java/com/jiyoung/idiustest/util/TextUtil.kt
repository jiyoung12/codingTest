package com.jiyoung.idiustest.util

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

object TextUtil{

    /**
     * 색상 및 볼드체 변경
     * @param context
     * @param textColor 변경할 색
     * @param fulltext 전체 텍스트
     * @param textToBold 변경할 텍스트 부분
     */
    fun getSectionOfTextBoldColor(
        context: Context, @ColorRes textColor: Int,
        fulltext: String,
        vararg textToBold: String
    ): SpannableStringBuilder {
        val builder = SpannableStringBuilder(fulltext)

        for (textItem in textToBold) {
            if (textItem.length > 0 && textItem.trim { it <= ' ' } != "") {
                //for counting start/end indexes
                val startingIndex = fulltext.indexOf(textItem)
                val endingIndex = startingIndex + textItem.length
                if (startingIndex >= 0 && endingIndex >= 0) {
                    builder.setSpan(StyleSpan(Typeface.BOLD), startingIndex, endingIndex, 0)
                }
            }
        }
        builder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, textColor)),
            0,
            fulltext.length,
            0
        )

        return builder
    }
}