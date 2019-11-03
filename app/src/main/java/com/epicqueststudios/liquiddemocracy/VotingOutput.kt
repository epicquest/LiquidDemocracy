package com.epicqueststudios.liquiddemocracy

import java.lang.StringBuilder

class VotingOutput {

    companion object {
        const val INVALID = "invalid"
    }

    private val votes: MutableMap<String, Int> = mutableMapOf()
    var invalidVotes = 0

    fun addVote(name: String?) {
        if (name == null){
            addInvalidVote()
        } else {
            votes[name] = (votes[name] ?: 0) + 1
        }
    }

    private fun addInvalidVote() {
        invalidVotes++
    }

    fun clear(){
        invalidVotes = 0
        votes.clear()
    }

    override fun toString(): String {
        val sorted = sortVotes()
        val sb = StringBuilder()
        for (vote in sorted){
            sb.append("${vote.value} ${vote.key}\n")
        }
        sb.append("$invalidVotes $INVALID")
        return sb.toString()
    }

    private fun sortVotes(): List<MutableMap.MutableEntry<String, Int>> {
        return votes.entries.sortedWith(compareByDescending { it.value})
    }

    fun addInvalidVotes(amount: Int) {
        invalidVotes+=amount
    }
}
