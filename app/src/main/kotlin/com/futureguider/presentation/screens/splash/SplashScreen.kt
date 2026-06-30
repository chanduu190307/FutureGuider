package com.futureguider.presentation.screens.splash

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.futureguider.R
import com.futureguider.presentation.components.FgButton
import com.futureguider.presentation.components.FgOutlinedButton
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onGetStarted: () -> Unit,
    onLogin: () -> Unit,
    onAutoLogin: () -> Unit,
    isLoggedIn: Boolean
) {
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            delay(600)
            onAutoLogin()
        }
    }

    var contentVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(200)
        contentVisible = true
    }

    // Gentle pulse animation for logo
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.04f,
        label = "pulse",
        animationSpec = infiniteRepeatable(
            tween(2000, easing = EaseInOutSine),
            RepeatMode.Reverse
        )
    )

    // White background matching the logo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = contentVisible,
            enter = fadeIn(tween(600)) + slideInVertically(tween(600)) { it / 5 }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // App Logo — exact match of uploaded image
                Image(
                    painter = painterResource(id = R.drawable.ic_splash_logo_png),
                    contentDescription = "Future Guider Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .scale(pulseScale)
                )

                Spacer(Modifier.height(24.dp))

                // App Name
                Text(
                    text = "FUTURE GUIDER",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1A1A1A),
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                // Divider lines like in logo
                Row(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF1A1A1A),
                        thickness = 1.5.dp
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        "✦",
                        color = Color(0xFF1A1A1A),
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.width(12.dp))
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF1A1A1A),
                        thickness = 1.5.dp
                    )
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Explore Your Future, One Step At A Time",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF555555),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.3.sp
                )

                Spacer(Modifier.height(56.dp))

                // Get Started button - dark to match logo
                Button(
                    onClick = onGetStarted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A1A1A)
                    )
                ) {
                    Text(
                        "Get Started",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }

                Spacer(Modifier.height(14.dp))

                // Login button - outlined
                OutlinedButton(
                    onClick = onLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF1A1A1A)
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.5.dp,
                        Color(0xFF1A1A1A)
                    )
                ) {
                    Text(
                        "Login",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }

                Spacer(Modifier.height(40.dp))

                // Education badge
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFFF1F1F1)
                ) {
                    Text(
                        text = "🎓  500+ Career Paths  •  32 Categories",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF555555)
                    )
                }
            }
        }
    }
}
