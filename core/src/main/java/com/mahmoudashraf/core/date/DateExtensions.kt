package com.mahmoudashraf.core.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.formatDate(
    from: String = "yyyy-MM-dd",
    to: String = "MMMM dd,yyyy"
): String {
    val simpleDateFormat = SimpleDateFormat(from)
    val date = simpleDateFormat.parse(this)
    val formattedDateAsDigitMonth = SimpleDateFormat(to, Locale.getDefault())
    return formattedDateAsDigitMonth.format(date ?: return "")
}