package com.example.albertsonapplication

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test

class FilterSearch {

    @Test
    fun validateAcronymPass() {
        assertEquals("renal transplant recipients", "renal transplant recipients")
    }

    @Test
    fun validateAcronymFail() {
        assertEquals(" transplant recipients", "renal transplant recipients")
    }

    @Test
    fun validateAcronymNoData() {
        assertEquals("renal transplant recipients", "")
    }
}