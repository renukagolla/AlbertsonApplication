package com.sample.project.models

data class LF (
    val lf: String,
    val freq: Long,
    val since: Long,
    val vars: List<LF>? = null
)