package com.epicqueststudios.liquiddemocracy.utils

import com.epicqueststudios.liquiddemocracy.models.Vote
import com.epicqueststudios.liquiddemocracy.VotingOutput
import java.util.*

class VotingEvaluator(private val votes: List<Vote>, private val invalid: List<String>) {

    fun evaluateVotes(): VotingOutput {
        val output = VotingOutput()
        val (_votes, duplicates) = removeDuplicates()
        _votes.forEach {
            output.addVote(evaluateVote(it, mutableListOf(), _votes))
        }
        output.addInvalidVotes(duplicates.size)
        output.addInvalidVotes(invalid.size)
        return output
    }

    private fun removeDuplicates(): Pair<List<Vote>, List<Vote>> {
        val duplicateNames =
            votes.groupingBy { it.name.toLowerCase(Locale.ROOT) }.eachCount().filter { it.value > 1 }.map { it.key }
        return votes.partition{ !duplicateNames.contains(it.name) }
    }

    private fun evaluateVote(
        vote: Vote?,
        delegation: MutableList<String>,
        _votes: List<Vote>
    ): String? {
        when (vote?.action) {
            Vote.ACTION.PICK -> return vote.voted
            Vote.ACTION.DELEGATE -> {
                if (!delegation.contains(vote.name)) {
                    delegation.add(vote.name)
                    return evaluateVote(_votes.firstOrNull { it.name.equals(vote.voted, ignoreCase = true)  },  delegation, _votes)
                }
            }
        }
        return null
    }
}