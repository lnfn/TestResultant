package com.eugenetereshkov.testresultant.entity

data class Currency(
        val name: String,
        val price: Price,
        val percentChange: Float,
        val volume: Int,
        val symbol: String
)