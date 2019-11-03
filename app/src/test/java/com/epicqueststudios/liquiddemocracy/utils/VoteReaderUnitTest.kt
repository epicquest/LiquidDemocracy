package com.epicqueststudios.liquiddemocracy.utils

import org.junit.Assert
import org.junit.Test

class VoteReaderUnitTest {

    @Test
    fun readProperFormattedFile_isCorrect() {
        val lines = VoteReader.readFile("example1.txt")
        val votes = VoteReader.readLines(lines)
        Assert.assertEquals(7, votes.first.size)
        Assert.assertEquals(0, votes.second.size)
    }

    @Test
    fun ignoreInvalidVotesFromFile_isCorrect() {
        val lines = VoteReader.readFile("example2.txt")
        val votes = VoteReader.readLines(lines)
        Assert.assertEquals(0, votes.first.size)
        Assert.assertEquals(5, votes.second.size)
    }

    @Test
    fun readVotesIgnoreCase_isCorrect() {
        val lines = VoteReader.readFile("example3.txt")
        val votes = VoteReader.readLines(lines)
        Assert.assertEquals(5, votes.first.size)
        Assert.assertEquals(0, votes.second.size)
    }
}