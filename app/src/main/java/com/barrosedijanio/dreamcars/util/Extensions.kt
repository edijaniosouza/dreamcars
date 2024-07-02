package com.barrosedijanio.dreamcars.util

import android.icu.text.DecimalFormat

fun String.formatToReal(): String {
    val floatValue = this.toFloatOrNull() ?: return "Valor inválido"
    val format = DecimalFormat("R$ #,##0.00")
    return format.format(floatValue)
}