package com.example.kotlindevcourse

/* [Task Step Data Model]
* To describe each step
* What? For dynamic routing of Task Detail Page
* Why? Some task has more steps than others, hence
* more page navigation than others
* */
data class TaskStep (
    val stepID: Int,
    val description: String
    /* TODO: Add more data to pass to route args */
    /* TODO: Image key */
    /* TODO: isFinalStep Flag */
)