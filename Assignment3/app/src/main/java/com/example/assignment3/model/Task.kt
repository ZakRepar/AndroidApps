package com.example.assignment3.model

data class Task (val description: String, val emoji: String?, val priority: String?, val opened: String? ) { //change types later

    override fun toString(): String {
        return "${description ?: " "}, ${emoji ?: " "}, ${priority ?: " "}, ${opened ?: " "}"
    }
} // end class