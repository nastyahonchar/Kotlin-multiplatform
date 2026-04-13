package com.example.kotlin_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform