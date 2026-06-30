package com.futureguider.presentation.screens.careertree

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.futureguider.domain.model.CareerNode
import com.futureguider.domain.model.NodeType
import com.futureguider.presentation.viewmodel.CareerTreeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareerTreeScreen(
    rootNodeId: Int,
    rootNodeName: String,
    onNodeSelected: (id: Int, name: String) -> Unit,
    onBack: () -> Unit,
    viewModel: CareerTreeViewModel = hiltViewModel()
) {
    LaunchedEffect(rootNodeId) {
        viewModel.setRootFilter(rootNodeId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val displayRoot = uiState.rootNodes.find { it.id == rootNodeId }

    LaunchedEffect(displayRoot) {
        displayRoot?.let {
            if (!viewModel.isExpanded(rootNodeId)) {
                viewModel.toggleNode(it)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            rootNodeName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Explore your career options",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
            ) {
                // Path guide header
                item {
                    PathGuideHeader(rootNodeName = rootNodeName, displayRoot = displayRoot)
                    Spacer(Modifier.height(12.dp))
                }

                // Children of root
                val rootChildren = uiState.childrenMap[rootNodeId] ?: emptyList()

                if (viewModel.isExpanded(rootNodeId) && rootChildren.isEmpty()) {
                    item {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                items(rootChildren, key = { it.id }) { child ->
                    TreeNodeItem(
                        node = child,
                        depth = 1,
                        viewModel = viewModel,
                        onLeafSelected = onNodeSelected
                    )
                }

                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }
}

@Composable
private fun PathGuideHeader(rootNodeName: String, displayRoot: CareerNode?) {
    val color = displayRoot?.let { parseColor(it.colorHex) } ?: Color(0xFF6366F1)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.10f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            Brush.radialGradient(listOf(color, color.copy(alpha = 0.6f))),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.School,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = rootNodeName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = color
                    )
                    Text(
                        text = "Select a path to explore →",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = color.copy(alpha = 0.2f))
            Spacer(Modifier.height(10.dp))

            Text(
                text = "💡 Tap any option below to expand and explore sub-paths. Tap a career to see full details.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun TreeNodeItem(
    node: CareerNode,
    depth: Int,
    viewModel: CareerTreeViewModel,
    onLeafSelected: (Int, String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isExpanded = node.id in uiState.expandedNodeIds
    val children = uiState.childrenMap[node.id] ?: emptyList()
    val color = parseColor(node.colorHex)
    val indentDp: Dp = (depth * 14).dp

    Column {
        // Connector line + node row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = indentDp, top = 3.dp, bottom = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Vertical connector line for depth > 1
            if (depth > 1) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(48.dp)
                        .background(color.copy(alpha = 0.25f))
                )
                Spacer(Modifier.width(8.dp))
            }

            // Node card
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        when (node.type) {
                            NodeType.LEAF   -> color.copy(alpha = 0.10f)
                            NodeType.BRANCH -> MaterialTheme.colorScheme.surfaceVariant.copy(0.8f)
                            NodeType.ROOT   -> color.copy(alpha = 0.08f)
                        }
                    )
                    .clickable {
                        when (node.type) {
                            NodeType.LEAF -> onLeafSelected(node.id, node.name)
                            else -> if (node.hasChildren) viewModel.toggleNode(node)
                        }
                    }
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dot indicator
                Box(
                    modifier = Modifier
                        .size(if (node.type == NodeType.LEAF) 10.dp else 8.dp)
                        .background(
                            when (node.type) {
                                NodeType.LEAF   -> color
                                NodeType.BRANCH -> color.copy(0.6f)
                                NodeType.ROOT   -> color
                            },
                            CircleShape
                        )
                )
                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = node.name,
                        style = when (node.type) {
                            NodeType.LEAF   -> MaterialTheme.typography.bodyLarge
                            NodeType.BRANCH -> MaterialTheme.typography.bodyLarge
                            NodeType.ROOT   -> MaterialTheme.typography.titleSmall
                        },
                        fontWeight = when (node.type) {
                            NodeType.LEAF   -> FontWeight.Medium
                            NodeType.BRANCH -> FontWeight.SemiBold
                            NodeType.ROOT   -> FontWeight.Bold
                        },
                        color = when (node.type) {
                            NodeType.LEAF -> color
                            else          -> MaterialTheme.colorScheme.onSurface
                        }
                    )
                    if (node.type == NodeType.BRANCH && !isExpanded) {
                        Text(
                            text = "Tap to expand options",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    if (node.type == NodeType.BRANCH && isExpanded) {
                        Text(
                            text = "Tap to collapse",
                            style = MaterialTheme.typography.labelSmall,
                            color = color.copy(alpha = 0.7f)
                        )
                    }
                }

                // Right side badge/icon
                when (node.type) {
                    NodeType.LEAF -> {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = color.copy(alpha = 0.18f)
                        ) {
                            Text(
                                "View Details →",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = color,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    else -> {
                        if (node.hasChildren) {
                            val rotation by animateFloatAsState(
                                targetValue = if (isExpanded) 90f else 0f,
                                animationSpec = tween(250),
                                label = "chevron"
                            )
                            Icon(
                                Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = if (isExpanded) color else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.rotate(rotation)
                            )
                        }
                    }
                }
            }
        }

        // Animated children
        AnimatedVisibility(
            visible = isExpanded && node.hasChildren,
            enter = expandVertically(tween(300)) + fadeIn(tween(300)),
            exit = shrinkVertically(tween(250)) + fadeOut(tween(200))
        ) {
            Column {
                if (children.isEmpty()) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = indentDp + 24.dp,
                                end = 16.dp,
                                top = 4.dp,
                                bottom = 4.dp
                            )
                    )
                }
                children.forEach { child ->
                    TreeNodeItem(
                        node = child,
                        depth = depth + 1,
                        viewModel = viewModel,
                        onLeafSelected = onLeafSelected
                    )
                }
            }
        }
    }
}

fun parseColor(hex: String): Color = try {
    Color(android.graphics.Color.parseColor(hex))
} catch (e: Exception) {
    Color(0xFF6366F1)
}
