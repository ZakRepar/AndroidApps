package com.example.assignment3.model

data class Task (val description: String, val emoji: String?, val priority: String?, val state: String? ) { //change types later

    override fun toString(): String {
        return "${emoji ?: " "}, ${priority ?: " "}, ${state ?: " "}"
    }
} // end class