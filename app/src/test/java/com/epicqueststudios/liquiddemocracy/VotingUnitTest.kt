package com.epicqueststudios.liquiddemocracy

import com.epicqueststudios.liquiddemocracy.utils.VoteReader
import com.epicqueststudios.liquiddemocracy.utils.VotingEvaluator
import org.junit.Assert
import org.junit.Test

class VotingUnitTest {

    @Test
    fun readVotesFromFileAndEvaluate_isCorrect() {
        val lines = VoteReader.readFile("example4.txt")
        val votes = VoteReader.readLines(lines)
        val helper = VotingEvaluator(votes.first, votes.second)
        val votingOutput = helper.evaluateVotes( )
        val output = votingOutput.toString()
        val outputLines = output.split("\n")
        val expected = listOf("2 Salad", "1 Pizza", "3 invalid")
        Assert.assertArrayEquals(expected.toTypedArray(), outputLines.toTypedArray())
    }
}