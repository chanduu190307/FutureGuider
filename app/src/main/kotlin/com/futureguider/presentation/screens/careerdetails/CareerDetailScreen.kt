@file:OptIn(ExperimentalLayoutApi::class)

package com.futureguider.presentation.screens.careerdetails

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.futureguider.domain.model.CareerDetail
import com.futureguider.presentation.components.FgButton
import com.futureguider.presentation.components.SectionHeader
import com.futureguider.presentation.screens.careertree.parseColor
import com.futureguider.presentation.theme.Indigo500
import com.futureguider.presentation.viewmodel.CareerDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareerDetailScreen(
    onBack: () -> Unit,
    onSavedPaths: () -> Unit,
    viewModel: CareerDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState.saveMessage) {
        uiState.saveMessage?.let { msg ->
            scope.launch { snackbarHostState.showSnackbar(msg) }
            viewModel.clearSaveMessage()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Career Details", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onSavedPaths) {
                        Icon(Icons.Default.Bookmarks, contentDescription = "Saved Paths")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.isLoading) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // Hero header
                CareerHeroSection(
                    careerName = uiState.careerName,
                    pathSteps = uiState.pathSteps
                )

                Spacer(Modifier.height(16.dp))

                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    uiState.detail?.let { detail ->
                        DescriptionCard(description = detail.description)
                        Spacer(Modifier.height(20.dp))

                        if (detail.skills.isNotEmpty()) {
                            SkillsSection(skills = detail.skills)
                            Spacer(Modifier.height(20.dp))
                        }

                        if (detail.projects.isNotEmpty()) {
                            ProjectsSection(detail = detail)
                            Spacer(Modifier.height(20.dp))
                        }

                        if (detail.certifications.isNotEmpty()) {
                            CertsSection(detail = detail)
                            Spacer(Modifier.height(20.dp))
                        }

                        NextStepCard(nextStep = detail.suggestedNextStep)
                        Spacer(Modifier.height(24.dp))
                    } ?: run {
                        EmptyDetailCard(careerName = uiState.careerName)
                        Spacer(Modifier.height(20.dp))
                    }

                    FgButton(
                        text = if (uiState.isSaved) "✓ Career Path Saved" else "Save Career Path",
                        onClick = { viewModel.saveCareerPath() },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !uiState.isSaved,
                        containerColor = if (uiState.isSaved)
                            MaterialTheme.colorScheme.tertiary
                        else
                            Indigo500
                    )
                    Spacer(Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
private fun CareerHeroSection(careerName: String, pathSteps: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1E1B4B), Color(0xFF312E81))
                )
            )
            .padding(20.dp)
    ) {
        Column {
            Text(
                careerName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            if (pathSteps.isNotEmpty()) {
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    pathSteps.forEachIndexed { index, step ->
                        Text(
                            step,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                        if (index < pathSteps.lastIndex) {
                            Icon(
                                Icons.Default.ChevronRight,
                                null,
                                tint = Color.White.copy(0.4f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DescriptionCard(description: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionHeader(title = "About this Career", emoji = "💡")
            Spacer(Modifier.height(10.dp))
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
private fun SkillsSection(skills: List<String>) {
    SectionHeader(title = "Required Skills", emoji = "🛠️")
    Spacer(Modifier.height(10.dp))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        skills.forEach { skill ->
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    skill,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun ProjectsSection(detail: CareerDetail) {
    SectionHeader(title = "Practice Projects", emoji = "🚀")
    Spacer(Modifier.height(10.dp))
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        detail.projects.forEachIndexed { i, project ->
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outline.copy(0.4f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "${i + 1}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            project.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (project.description.isNotBlank()) {
                            Spacer(Modifier.height(4.dp))
                            Text(
                                project.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CertsSection(detail: CareerDetail) {
    SectionHeader(title = "Certifications", emoji = "🏅")
    Spacer(Modifier.height(10.dp))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        detail.certifications.forEach { cert ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(0.5f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Verified,
                        null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(
                            cert.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        if (cert.provider.isNotBlank()) {
                            Text(
                                cert.provider,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NextStepCard(nextStep: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionHeader(title = "Suggested Next Step", emoji = "🎯")
            Spacer(Modifier.height(10.dp))
            Text(
                nextStep,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
private fun EmptyDetailCard(careerName: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🔍", fontSize = 40.sp)
            Spacer(Modifier.height(10.dp))
            Text(
                "$careerName is a great choice!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Detailed information for this career path is coming soon. Save it to revisit later!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
