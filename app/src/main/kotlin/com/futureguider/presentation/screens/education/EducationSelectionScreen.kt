package com.futureguider.presentation.screens.education

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.futureguider.presentation.viewmodel.SavedPathsViewModel

private data class EducationOption(
    val id: Int,
    val name: String,
    val emoji: String,
    val subtitle: String,
    val category: String,
    val gradient: List<Color>
)

private val educationOptions = listOf(
    // AFTER 10TH
    EducationOption(1,   "10th Passed",         "🎒", "Just completed 10th grade",                    "After 10th",           listOf(Color(0xFF6366F1), Color(0xFF4F46E5))),
    // AFTER 12TH
    EducationOption(2,   "12th Science",        "🔬", "PCM / PCB stream completed",                   "After 12th",           listOf(Color(0xFF8B5CF6), Color(0xFF7C3AED))),
    EducationOption(3,   "12th Commerce",       "📊", "Commerce stream completed",                    "After 12th",           listOf(Color(0xFFEC4899), Color(0xFFDB2777))),
    EducationOption(4,   "12th Arts",           "🎨", "Arts / Humanities stream completed",           "After 12th",           listOf(Color(0xFFF59E0B), Color(0xFFD97706))),
    // DIPLOMA / VOCATIONAL
    EducationOption(5,   "Diploma / ITI",       "🔧", "Diploma, ITI or vocational course",           "Diploma / Vocational",  listOf(Color(0xFF10B981), Color(0xFF059669))),
    // UG DEGREES
    EducationOption(6,   "BCA",                 "💻", "Bachelor of Computer Applications",            "Under Graduate",       listOf(Color(0xFF3B82F6), Color(0xFF2563EB))),
    EducationOption(7,   "B.Sc",                "🧪", "Bachelor of Science",                          "Under Graduate",       listOf(Color(0xFF14B8A6), Color(0xFF0D9488))),
    EducationOption(8,   "B.Com",               "💰", "Bachelor of Commerce",                         "Under Graduate",       listOf(Color(0xFFF97316), Color(0xFFEA580C))),
    EducationOption(9,   "B.Tech / BE",         "⚙️", "Bachelor of Technology / Engineering",         "Under Graduate",       listOf(Color(0xFF6366F1), Color(0xFF4F46E5))),
    EducationOption(11,  "B.Ed",                "📚", "Bachelor of Education",                        "Under Graduate",       listOf(Color(0xFF7C3AED), Color(0xFF6D28D9))),
    // PG DEGREES
    EducationOption(10,  "MBA",                 "🏢", "Master of Business Administration",            "Post Graduate",        listOf(Color(0xFFDC2626), Color(0xFFB91C1C))),
    // PROFESSIONAL COURSES
    EducationOption(300, "Hotel Management",    "🏨", "Hospitality, culinary arts & event management","Professional Courses",  listOf(Color(0xFFB91C1C), Color(0xFF991B1B))),
    EducationOption(301, "Design Courses",      "✏️", "Fashion, product, graphic & industrial design", "Professional Courses", listOf(Color(0xFF7C3AED), Color(0xFF6D28D9))),
    EducationOption(302, "Architecture",        "🏗️", "B.Arch, urban planning & landscape design",    "Professional Courses",  listOf(Color(0xFF0369A1), Color(0xFF075985))),
    EducationOption(303, "Agriculture",         "🌾", "Agri science, agritech & food technology",     "Professional Courses",  listOf(Color(0xFF15803D), Color(0xFF166534))),
    EducationOption(304, "Law & Legal",         "⚖️", "BA LLB, LLB, LLM & legal career paths",        "Professional Courses",  listOf(Color(0xFF1E3A5F), Color(0xFF1E40AF))),
    EducationOption(305, "Media & Journalism",  "📺", "Journalism, TV, digital media & filmmaking",   "Professional Courses",  listOf(Color(0xFF9D174D), Color(0xFF831843))),
    EducationOption(306, "Paramedical",         "🩺", "EMT, OT tech, optometry & clinical psych",     "Professional Courses",  listOf(Color(0xFF065F46), Color(0xFF064E3B))),
    EducationOption(307, "Aviation",            "✈️", "Pilot, cabin crew & aircraft maintenance",      "Professional Courses",  listOf(Color(0xFF1D4ED8), Color(0xFF1E40AF))),
    EducationOption(308, "Marine & Merchant",   "🚢", "Merchant navy & marine engineering",           "Professional Courses",  listOf(Color(0xFF0C4A6E), Color(0xFF0369A1))),
    EducationOption(309, "Social Work",         "🤝", "MSW, community development & NGO careers",     "Professional Courses",  listOf(Color(0xFF92400E), Color(0xFF78350F))),
    EducationOption(310, "Finance & Accounts",  "📈", "CA, CMA, actuary, trading & insurance",        "Professional Courses",  listOf(Color(0xFF064E3B), Color(0xFF065F46))),
    EducationOption(311, "Environmental Sci",   "🌿", "Env science, renewable energy & wildlife",     "Professional Courses",  listOf(Color(0xFF166534), Color(0xFF14532D))),
    EducationOption(312, "Veterinary Science",  "🐾", "BVSc, wildlife biology & animal nutrition",    "Professional Courses",  listOf(Color(0xFF78350F), Color(0xFF92400E))),
    EducationOption(313, "Library Science",     "📖", "Librarian, digital archivist & knowledge mgmt","Professional Courses",  listOf(Color(0xFF312E81), Color(0xFF3730A3))),
    // CAREER PATHS
    EducationOption(130, "Government Jobs",     "🏛️", "UPSC, SSC, Banking, Railways & PSU careers",   "Career Paths",         listOf(Color(0xFF1D4ED8), Color(0xFF1E40AF))),
    EducationOption(131, "Defence & Military",  "🎖️", "Indian Army, Navy, Air Force & Paramilitary",  "Career Paths",         listOf(Color(0xFF065F46), Color(0xFF064E3B))),
    EducationOption(132, "Sports & Fitness",    "⚽", "Professional sports, coaching & wellness",     "Career Paths",         listOf(Color(0xFFB45309), Color(0xFF92400E))),
    EducationOption(133, "Entrepreneurship",    "🚀", "Startups, business & freelancing careers",     "Career Paths",         listOf(Color(0xFF92400E), Color(0xFF78350F))),
    EducationOption(134, "Creative Arts",       "🎭", "Design, music, film, writing & performing",    "Career Paths",         listOf(Color(0xFF5B21B6), Color(0xFF4C1D95))),
    EducationOption(135, "Healthcare Allied",   "🏥", "Nursing, therapy, diagnostics & allied health","Career Paths",         listOf(Color(0xFF047857), Color(0xFF064E3B)))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationSelectionScreen(
    onSelect: (id: Int, name: String) -> Unit,
    onSavedPaths: () -> Unit,
    onLogout: () -> Unit,
    savedPathsViewModel: SavedPathsViewModel = hiltViewModel()
) {
    val userName by savedPathsViewModel.userName.collectAsStateWithLifecycle()
    val categories = educationOptions.groupBy { it.category }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "Hi, ${userName.ifBlank { "Explorer" }} 👋",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "What's your education level?",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSavedPaths) {
                        Icon(Icons.Default.BookmarkBorder, "Saved Paths")
                    }
                    IconButton(onClick = onLogout) {
                        Icon(Icons.Default.Logout, "Logout")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // Hero question card
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0xFF1E1B4B), Color(0xFF312E81))
                            )
                        )
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🎯", fontSize = 44.sp)
                        Spacer(Modifier.height(10.dp))
                        Text(
                            "Find Your Perfect Career Path",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Select your current education level and explore\nhundreds of career options tailored for you",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // Stats bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(0.3f))
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatChip("32", "Categories")
                    StatChip("400+", "Career Paths")
                    StatChip("160+", "Detailed Guides")
                }
            }

            // Render each category section
            categories.forEach { (categoryName, options) ->
                item {
                    CategoryHeader(title = categoryName)
                }
                itemsIndexed(options) { _, option ->
                    EducationOptionRow(
                        option = option,
                        onClick = { onSelect(option.id, option.name) }
                    )
                }
                item { Spacer(Modifier.height(8.dp)) }
            }
        }
    }
}

@Composable
private fun StatChip(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CategoryHeader(title: String) {
    val emoji = when (title) {
        "After 10th" -> "🎒"
        "After 12th" -> "📚"
        "Diploma / Vocational" -> "🔧"
        "Under Graduate" -> "🎓"
        "Post Graduate" -> "🏫"
        "Professional Courses" -> "💼"
        "Career Paths" -> "🗺️"
        else -> "📂"
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(emoji, fontSize = 16.sp)
        Spacer(Modifier.width(8.dp))
        Text(
            title,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(8.dp))
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.outline.copy(0.3f)
        )
    }
}

@Composable
private fun EducationOptionRow(
    option: EducationOption,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.97f else 1f,
        animationSpec = tween(100),
        label = "scale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .scale(scale)
            .clickable {
                pressed = true
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gradient circle with emoji
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        Brush.linearGradient(option.gradient),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(option.emoji, fontSize = 24.sp)
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = option.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = option.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Arrow button
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(
                        option.gradient.first().copy(alpha = 0.12f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = option.gradient.first(),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
