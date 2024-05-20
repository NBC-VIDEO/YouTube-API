package com.nbc.video

import java.text.DecimalFormat

fun Decimal(number: Int): String? {
    val decimal = DecimalFormat("#,###")
    return decimal.format(number)
}