package com.epicqueststudios.liquiddemocracy.utils

import com.epicqueststudios.liquiddemocracy.models.Vote
import org.junit.Test

import org.junit.Assert.*

class VotingEvaluatorUnitTest {

    @Test
    fun recogniseCycles_isCorrect() {
        val votes = mutableListOf<Vote>()
        votes.add(
            Vote(
                "a1",
                Vote.ACTION.DELEGATE,
                "a1"
            )
        )
        votes.add(
            Vote(
                "b1",
                Vote.ACTION.DELEGATE,
                "b2"
            )
        )
        votes.add(
            Vote(
                "b2",
                Vote.ACTION.DELEGATE,
                "b1"
            )
        )
        votes.add(
            Vote(
                "c1",
                Vote.ACTION.DELEGATE,
                "c2"
            )
        )
        votes.add(
            Vote(
                "c2",
                Vote.ACTION.DELEGATE,
                "c3"
            )
        )
        votes.add(
            Vote(
                "c3",
                Vote.ACTION.DELEGATE,
                "c1"
            )
        )
        val helper = VotingEvaluator(votes, emptyList())
        val votingOutput = helper.evaluateVotes()
        val output = votingOutput.toString()
        val lines = output.split("\n")

        val expected = listOf("6 invalid")
        assertArrayEquals(expected.toTypedArray(), lines.toTypedArray())
    }

    @Test
    fun recogniseDuplicates_isCorrect() {
        val votes = mutableListOf<Vote>()
        votes.add(
            Vote(
                "a",
                Vote.ACTION.PICK,
                "aa"
            )
        )
        votes.add(
            Vote(
                "a",
                Vote.ACTION.PICK,
                "bb"
            )
        )
        votes.add(
            Vote(
                "b",
                Vote.ACTION.DELEGATE,
                "c"
            )
        )
        votes.add(
            Vote(
                "b",
                Vote.ACTION.PICK,
                "aa"
            )
        )
        votes.add(
            Vote(
                "c",
                Vote.ACTION.DELEGATE,
                "b"
            )
        )
        val helper = VotingEvaluator(votes, emptyList())
        val votingOutput = helper.evaluateVotes()

        val output = votingOutput.toString()
        val lines = output.split("\n")
        val expected = listOf("5 invalid")
        assertArrayEquals(expected.toTypedArray(), lines.toTypedArray())
    }
}
