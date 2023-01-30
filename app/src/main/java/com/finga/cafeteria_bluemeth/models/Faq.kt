package com.finga.cafeteria_bluemeth.models

data class Faq(
    val question: String,
    val answer: String,
    val isExpanded: Boolean = false,
)
