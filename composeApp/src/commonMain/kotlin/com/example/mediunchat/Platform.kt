package com.example.mediunchat

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform