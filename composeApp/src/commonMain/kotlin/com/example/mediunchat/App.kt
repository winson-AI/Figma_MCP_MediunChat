package com.example.mediunchat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mediunchat.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.shape.CircleShape
import org.jetbrains.compose.ui.tooling.preview.Preview

// Data classes for the screen
data class Person(
    val name: String,
    val avatar: String,
    val onlineStatus: String? = null, // null means no online status shown
    val isOnline: Boolean = false
)

@Composable
@Preview
fun App() {
    val people = listOf(
        Person("Martha Craig", "avatar_martha_craig.png", isOnline = true),
        Person("Jamie Franco", "avatar_jamie_franco.png", isOnline = true),
        Person("Kieron Dotson", "avatar_kieron_dotson.png", onlineStatus = "8 min.", isOnline = true),
        Person("Zack John", "avatar_zack_john.png", onlineStatus = "10 min.", isOnline = true),
        Person("Tabitha Potter", "avatar_tabitha_potter.png", onlineStatus = "10 min.", isOnline = true),
        Person("Albert Lasker", "avatar_albert_lasker.png", onlineStatus = "30 min.", isOnline = true)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Status Bar
            item {
                StatusBar()
            }

            // Top Bar
            item {
                TopBar()
            }

            // Search Bar
            item {
                SearchBar()
            }

            // Your Story
            item {
                YourStory()
            }

            // People List with separators
            val firstTwo = people.take(2)
            items(firstTwo.size) { index ->
                PersonItem(firstTwo[index])
                if (index < firstTwo.size - 1) {
                    Separator()
                }
            }

            // Recently Active Header
            item {
                Text(
                    text = "Recently active",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = (-0.08).sp,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            // Recently Active People with separators
            val lastFour = people.drop(2)
            items(lastFour.size) { index ->
                PersonItem(lastFour[index])
                if (index < lastFour.size - 1) {
                    Separator()
                }
            }

            // Tab Bar
            item {
                TabBar()
            }

            // Home Indicator
            item {
                HomeIndicator()
            }
        }
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .background(Color(0xFF111111).copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Profile avatar
            Image(
                painter = painterResource(Res.drawable.avatar_profile_main),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Title
            Text(
                text = "People",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.4.sp
            )

            // Action buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_requests),
                    contentDescription = "Requests",
                    modifier = Modifier.size(40.dp)
                )
                Image(
                    painter = painterResource(Res.drawable.ic_add_contact),
                    contentDescription = "Add Contact",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(horizontal = 16.dp)
                .background(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(15.5.dp, 15.7.dp)
                )
                Text(
                    text = "Search",
                    color = Color(0xFF8E8E93),
                    fontSize = 17.sp,
                    letterSpacing = (-0.41).sp
                )
            }
        }
    }
}

@Composable
fun YourStory() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color.Black)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Story group icon
        Image(
            painter = painterResource(Res.drawable.ic_story_group),
            contentDescription = "Story Group",
            modifier = Modifier.size(40.dp)
        )

        Column {
            Text(
                text = "Your story",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.2).sp
            )
            Text(
                text = "Add to your story",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 13.sp,
                letterSpacing = (-0.08).sp
            )
        }
    }
}

@Composable
fun PersonItem(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Black)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Avatar with online indicator
        Box {
            Image(
                painter = painterResource(
                    when (person.avatar) {
                        "avatar_martha_craig.png" -> Res.drawable.avatar_martha_craig
                        "avatar_jamie_franco.png" -> Res.drawable.avatar_jamie_franco
                        "avatar_kieron_dotson.png" -> Res.drawable.avatar_kieron_dotson
                        "avatar_zack_john.png" -> Res.drawable.avatar_zack_john
                        "avatar_tabitha_potter.png" -> Res.drawable.avatar_tabitha_potter
                        "avatar_albert_lasker.png" -> Res.drawable.avatar_albert_lasker
                        else -> Res.drawable.avatar_profile_main
                    }
                ),
                contentDescription = person.name,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Online indicator (green dot)
            if (person.isOnline && person.onlineStatus == null) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.BottomEnd)
                        .background(Color(0xFFC7F0BB), CircleShape)
                )
            } else {
                // Online status badge
                person.onlineStatus?.let { status ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(Color(0xFFC7F0BB), RoundedCornerShape(5.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = status,
                            color = Color.Black,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.3.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                text = person.name,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.2).sp
            )

        }

        // Wave button
        Image(
            painter = painterResource(Res.drawable.ic_wave),
            contentDescription = "Wave",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun TabBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp)
            .background(Color(0xFF111111).copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 34.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_tab1),
                contentDescription = "Tab 1",
                modifier = Modifier.size(24.dp, 24.04.dp)
            )

            Image(
                painter = painterResource(Res.drawable.ic_tab2),
                contentDescription = "Tab 2",
                modifier = Modifier.size(30.dp, 22.08.dp)
            )

            Image(
                painter = painterResource(Res.drawable.ic_tab3),
                contentDescription = "Tab 3",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun Separator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.White.copy(alpha = 0.1f))
    )
}

@Composable
fun HomeIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .width(134.dp)
                .height(5.dp)
                .align(Alignment.BottomCenter)
                .background(Color.White, RoundedCornerShape(100.dp))
        )
    }
}

@Composable
fun StatusBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(Color.Black)
            .padding(horizontal = 21.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "9:41",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = (-0.3).sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_cellular),
                contentDescription = "Cellular",
                modifier = Modifier.size(17.dp, 10.67.dp)
            )
            Image(
                painter = painterResource(Res.drawable.ic_wifi),
                contentDescription = "WiFi",
                modifier = Modifier.size(15.33.dp, 10.99.dp)
            )
            Image(
                painter = painterResource(Res.drawable.ic_battery),
                contentDescription = "Battery",
                modifier = Modifier.size(24.33.dp, 11.33.dp)
            )
        }
    }
}