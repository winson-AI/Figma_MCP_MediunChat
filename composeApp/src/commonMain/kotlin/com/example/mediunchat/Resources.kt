package com.example.mediunchat

import androidx.compose.ui.graphics.Color

// Temporary resource access until proper drawable resources are fixed
object AppResources {
    val avatarColors = listOf(
        Color(0xFF2196F3), // Blue
        Color(0xFF4CAF50), // Green
        Color(0xFFFF9800), // Orange
        Color(0xFFE91E63), // Pink
        Color(0xFF9C27B0), // Purple
        Color(0xFF00BCD4), // Cyan
    )

    fun getAvatarColorForName(name: String): Color {
        val index = name.hashCode().mod(avatarColors.size)
        return avatarColors[index]
    }
}
