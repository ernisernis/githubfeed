package com.ernisernis.githubfeed

import com.ernisernis.githubfeed.BuildConfig.BASE_URL
import com.ernisernis.githubfeed.core.data.networking.constructUrl
import org.junit.Test

import org.junit.Assert.*

class ConstructUrlKtTest {

    private val testCases = listOf(
        Pair(BASE_URL, BASE_URL),
        Pair("/feeds", "${BASE_URL}feeds"),
        Pair("feeds", "${BASE_URL}feeds"),
        Pair("/feeds/31/abc", "${BASE_URL}feeds/31/abc"),
        Pair("31feeds", "${BASE_URL}31feeds"),
        Pair("", BASE_URL),
    )

    @Test
    fun `the string should be constructed properly to include base URL`() {
        testCases.forEach { (value, expected) ->
            val actual = constructUrl(value)

            assertEquals("constructed URL should be $expected", expected, actual)
        }
    }
}