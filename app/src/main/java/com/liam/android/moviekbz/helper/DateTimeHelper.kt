package com.liam.android.moviekbz.helper

import com.liam.android.moviekbz.helper.DateTimeHelper
import android.text.TextUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.JvmOverloads

/**
 * Created by ZMN on 4/6/18.
 */
object DateTimeHelper {
    const val SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val SERVER_DATE_TIME_12_HR_FORMAT = "MM/dd/yyyy hh:mm:ss a"
    const val LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a"
    const val LOCAL_DATE_TIME_DISPLAY_FORMAT = "dd MMM, yyyy hh:mm a"
    const val LOCAL_DATE_TIME_FILE_FORMAT = "ddMMyyyyHHmmss"
    const val LOCAL_DATE_FORMAT = "dd/MM/yyyy"
    const val LOCAL_DATE_DISPLAY_FORMAT = "dd MMM, yyyy"
    const val LOCAL_TIME_FORMAT = "hh:mm a"
    const val SERVER_DATE_FORMAT = "yyyy-MM-dd"
    const val SERVER_TIME_FORMAT = "HH:mm:ss"
    private val TAG = DateTimeHelper::class.java.simpleName
    fun getDateFromString(strDate: String, dateFormat: String): Date? {
        return getDateFromString(strDate, dateFormat, Locale.US)
    }

    fun getDateFromString(strDate: String, dateFormat: String, locale: Locale?): Date? {
        var locale = locale
        Log.d(
            TAG,
            "getDateFromString() called with: strDate = [$strDate], dateFormat = [$dateFormat], locale = [$locale]"
        )
        if (TextUtils.isEmpty(strDate) || TextUtils.isEmpty(dateFormat)) {
            return null
        }
        if (locale == null) {
            locale = Locale.US
        }
        val sdf = SimpleDateFormat(dateFormat, locale)
        return try {
            var cleanedDate = strDate.replace("T", " ")
            if (cleanedDate.contains(".")) {
                cleanedDate = cleanedDate.substring(0, cleanedDate.indexOf("."))
            }
            Log.d(TAG, "getDateFromString: Remove T $cleanedDate")
            sdf.parse(cleanedDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @JvmOverloads
    fun formatDate(date: Date?, format: String?, locale: Locale? = Locale.US): String {
        var locale = locale
        if (date == null || TextUtils.isEmpty(format)) {
            return ""
        }
        if (locale == null) {
            locale = Locale.US
        }
        val sdf = SimpleDateFormat(format, locale)
        return sdf.format(date)
    }

    fun convertDateFormat(
        sourceDate: String,
        sourceFormat: String,
        destinationFormat: String?
    ): String {
        val date = getDateFromString(sourceDate, sourceFormat)
        return date?.let { formatDate(it, destinationFormat) } ?: ""
    }

    fun validateDateFormat(dateToValidate: String?, dateFormat: String?): Boolean {
        Log.d(
            TAG,
            "validateDateFormat() called with: dateToValidate = [$dateToValidate], dateFormat = [$dateFormat]"
        )
        if (dateToValidate == null || dateToValidate.isEmpty() || dateFormat == null || dateFormat.isEmpty()) {
            return false
        }
        val sdf = SimpleDateFormat(dateFormat, Locale.US)
        sdf.isLenient = false
        return try {
            //if not valid, it will throw ParseException
            val date = sdf.parse(dateToValidate.replace("T", " "))
            Log.d(TAG, "validateDateFormat: VALID")
            true
        } catch (e: ParseException) {
            Log.d(
                TAG,
                "validateDateFormat() NOT VALID: dateToValidate = [$dateToValidate], dateFormat = [$dateFormat]"
            )
            false
        }
    }

    fun isAfterToday(targetDate: String, dateFormat: String): Boolean {
        return isAfterToday(getDateFromString(targetDate, dateFormat))
    }

    fun isAfterToday(targetDate: Date?): Boolean {
        return targetDate != null && Date().compareTo(targetDate) < 0
    }

    fun isSameDay(day1: Date?, day2: Date?): Boolean {
        if (day1 != null && day2 != null) {
            val c1 = Calendar.getInstance()
            c1.time = day1
            val c2 = Calendar.getInstance()
            c2.time = day2
            val day1Day = c1[Calendar.DAY_OF_MONTH]
            val day1Month = c1[Calendar.MONTH]
            val day1Year = c1[Calendar.YEAR]
            val day2Day = c2[Calendar.DAY_OF_MONTH]
            val day2Month = c2[Calendar.MONTH]
            val day2Year = c2[Calendar.YEAR]
            return day1Day == day2Day && day1Month == day2Month && day1Year == day2Year
        }
        return false
    }
}