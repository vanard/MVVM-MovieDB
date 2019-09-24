package com.vanard.ovotask.utils

import com.vanard.ovotask.utils.FormatDate.DATE_FORMAT_OUTPUT
import com.vanard.ovotask.utils.FormatDate.reformatStringDate
import org.junit.Assert
import org.junit.Test

class DateFormatTest {
    @Test
    fun dateStringFormatTest(){
        Assert.assertEquals(
            "05 November 2018",
            reformatStringDate("2018-11-05", "yyyy-MM-dd", DATE_FORMAT_OUTPUT)
        )

        Assert.assertEquals(
            "Mon",
            reformatStringDate("2018-11-05", "yyyy-MM-dd", "EEE")
        )
    }
}