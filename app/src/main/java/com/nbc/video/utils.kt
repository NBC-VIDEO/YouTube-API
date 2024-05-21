package com.nbc.video

import java.text.DecimalFormat

fun decimal(number: Int): String? {
    val decimal = DecimalFormat("#,###")
    return decimal.format(number)
}