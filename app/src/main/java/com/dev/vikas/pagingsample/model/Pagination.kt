package com.dev.vikas.pagingsample.model

data class Pagination(
    val limit: Int? = 0,
    val links: Links?,
    val page: Int? = 0,
    val pages: Int? = 0,
    val total: Int? = 0
)