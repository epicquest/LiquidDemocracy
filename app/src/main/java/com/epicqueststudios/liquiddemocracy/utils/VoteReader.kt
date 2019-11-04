package com.epicqueststudios.liquiddemocracy.utils

import com.epicqueststudios.liquiddemocracy.models.Vote
import java.io.File
import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE

/**
 * VoteReader is helper for reading votes from file (@see com.epicqueststudios.liquiddemocracy.models.Vote)
 * Line as syntactically valid, if contains word "DELEGATE" or "PICK" with at least one no space character before and after this word.
 * All words are case insensitive.
 */

object VoteReader {
    private val VOTE_PATTERN = Pattern.compile("(.+[\\w]+)( DELEGATE | PICK )(.+[\\w]+)", CASE_INSENSITIVE)

    /**
     * Function is used to read file and convert it to list of strings
     * @param filename
     */

    fun readFile(filename:String): List<String>{
        val uri = this.javaClass.classLoader!!.getResource(filename)
        return File(uri.toURI()).useLines{l ->
            return l.toList()
        }
    }

    /**
     * Function used to parse list of lines to pair of syntactically valid votes and list of invalid lines.
     * Line as syntactically valid, if contains word "DELEGATE" or "PICK" with at least one no space character before and after this word.
     * All words are case insensitive.
     *
     *  @param lines
     */
    fun readLines(lines: List<String>): Pair<List<Vote>, List<String>>{
        val votes = mutableListOf<Vote>()
        val (valid, invalid) = lines.partition {line -> VOTE_PATTERN.toRegex().matches(line)}
        valid.forEach {
            val matcher = VOTE_PATTERN.matcher(it)
            if (matcher.find()) {
                votes.add(
                    Vote(
                        matcher.group(1),
                        Vote.ACTION.parse(
                            matcher.group(
                                2
                            ).trim()
                        ),
                        matcher.group(3)
                    )
                )
            } else {
                throw NotImplementedError()
            }
        }
        return Pair(votes, invalid)
    }
}