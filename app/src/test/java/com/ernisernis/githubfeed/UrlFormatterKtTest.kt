package com.ernisernis.githubfeed


import com.ernisernis.githubfeed.core.presentation.util.formatUrlWithReplacements
import org.junit.Test

import org.junit.Assert.*

class UrlFormatterKtTest {

    @Test
    fun `formatUrlWithReplacements returns null when url is null`() {
        val result = formatUrlWithReplacements(null, "replacement")
        assertNull(result)
    }

    @Test
    fun `formatUrlWithReplacements returns itself when no replacements provided`() {
        val url = "https://example.com/feed"
        val result = formatUrlWithReplacements(url)
        assertEquals(url, result)
    }

    @Test
    fun `formatUrlWithReplacements replaces single placeholder`() {
        val url = "https://example.com/{category}/feed"
        val result = formatUrlWithReplacements(url, "tech")
        assertEquals("https://example.com/tech/feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements replaces multiple placeholders`() {
        val url = "https://example.com/{category}/{subcategory}/feed"
        val result = formatUrlWithReplacements(url, "tech", "software")
        assertEquals("https://example.com/tech/software/feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements handles placeholders with content`() {
        val url = "https://example.com/{category-id}/feed"
        val result = formatUrlWithReplacements(url, "12345")
        assertEquals("https://example.com/12345/feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements ignores excess replacements`() {
        val url = "https://example.com/{category}/feed"
        val result = formatUrlWithReplacements(url, "tech", "unused", "another-unused")
        assertEquals("https://example.com/tech/feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements leaves placeholders when not enough replacements`() {
        val url = "https://example.com/{category}/{subcategory}/feed"
        val result = formatUrlWithReplacements(url, "tech")
        assertEquals("https://example.com/tech/{subcategory}/feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements handles empty placeholder values`() {
        val url = "https://example.com/{category}/feed"
        val result = formatUrlWithReplacements(url, "")
        assertEquals("https://example.com//feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements correctly handles multiple replacements with different lengths`() {
        val url = "https://example.com/{short}/{verylongplaceholder}/feed"
        val result = formatUrlWithReplacements(url, "x", "longvalue")
        assertEquals("https://example.com/x/longvalue/feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements correctly handles special characters in replacements`() {
        val url = "https://example.com/{category}/feed"
        val result = formatUrlWithReplacements(url, "tech?page=1&size=10")
        assertEquals("https://example.com/tech?page=1&size=10/feed.atom", result)
    }

    @Test
    fun `formatUrlWithReplacements handles url with no placeholders`() {
        val url = "https://example.com/static/feed"
        val result = formatUrlWithReplacements(url, "unused")
        assertEquals("${url}.atom", result)
    }
}