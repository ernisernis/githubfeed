package com.ernisernis.githubfeed

import com.ernisernis.githubfeed.core.presentation.util.validateContentType
import org.junit.Test

import org.junit.Assert.*

class ValidateContentTypeKtTest {

    @Test
    fun `validateContentType returns itself`() {
        val input = "application/atom+xml"
        val result = input.validateContentType()
        assertEquals(input, result)
    }

    @Test
    fun `validateContentType returns null on non atom+xml input`() {
        val input = "application/json"
        val result = input.validateContentType()
        assertNull(result)
    }

    @Test
    fun `validateContentType returns null on empty input`() {
        val input = ""
        val result = input.validateContentType()
        assertNull(result)
    }

    @Test
    fun `validateContentType returns null on random input`() {
        val input = "random-content-type"
        val result = input.validateContentType()
        assertNull(result)
    }
}