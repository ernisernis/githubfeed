package com.ernisernis.githubfeed.core.presentation.util


/*
    Use regular expression to replace every placeholder inside '{}'
    For every vararg, it is going to search for first placeholder with '{}'
    e.g.:
        - url = "https://github.com/{user}", replacement = "test"
            - output: "https://github.com/test"

        - url = "https://github.com/{user11}, replacement = "nice"
            - output: "https://github.com/nice"

        - url = "https://github.com/{user}/{repo}", replacement = "test", "abc1"
            - output: "https://github.com/test/abc1"

        - url = "https://github.com/{user}/{repo}/categories/{category}", replacement = "test", "abc1", "book"
            - output: "https://github.com/test/abc1/cateogories/book"

    Also, we add ".atom" to the end of the url so we can fetch Atom contentType out of it
 */
fun formatUrlWithReplacements(
    url: String?,
    vararg replacements: String
): String? {
    if (url == null) return null
    if (replacements.isEmpty()) return url

    val pattern = Regex("\\{[^}]*\\}")
    var result = "${url}.atom"
    var offset = 0

    pattern.findAll(url).forEachIndexed { index, matchResult ->
        if (index < replacements.size) {
            val replacement = replacements[index]
            val start = matchResult.range.first + offset
            val end = matchResult.range.last + 1 + offset

            result = result!!.replaceRange(start, end, replacement)

            offset += replacement.length - (matchResult.range.last - matchResult.range.first + 1)
        }
    }

    return result
}