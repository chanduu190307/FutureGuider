package com.futureguider.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.futureguider.presentation.screens.auth.LoginScreen
import com.futureguider.presentation.screens.auth.RegisterScreen
import com.futureguider.presentation.screens.careerdetails.CareerDetailScreen
import com.futureguider.presentation.screens.careertree.CareerTreeScreen
import com.futureguider.presentation.screens.education.EducationSelectionScreen
import com.futureguider.presentation.screens.savedpaths.SavedPathsScreen
import com.futureguider.presentation.screens.splash.SplashScreen
import com.futureguider.presentation.viewmodel.AuthViewModel

object Routes {
    const val SPLASH          = "splash"
    const val LOGIN           = "login"
    const val REGISTER        = "register"
    const val EDUCATION       = "education"
    const val CAREER_TREE     = "career_tree/{rootNodeId}/{rootNodeName}"
    const val CAREER_DETAIL   = "career_detail/{nodeId}/{nodeName}"
    const val SAVED_PATHS     = "saved_paths"

    fun careerTree(rootNodeId: Int, rootNodeName: String) =
        "career_tree/$rootNodeId/${rootNodeName.encode()}"
    fun careerDetail(nodeId: Int, nodeName: String) =
        "career_detail/$nodeId/${nodeName.encode()}"

    private fun String.encode() = java.net.URLEncoder.encode(this, "UTF-8")
}

private fun enterTransition(): EnterTransition = slideIn(tween(300)) {
    IntOffset(it.width, 0)
} + fadeIn(tween(300))

private fun exitTransition(): ExitTransition = slideOut(tween(300)) {
    IntOffset(-it.width / 3, 0)
} + fadeOut(tween(200))

private fun popEnterTransition(): EnterTransition = slideIn(tween(300)) {
    IntOffset(-it.width, 0)
} + fadeIn(tween(300))

private fun popExitTransition(): ExitTransition = slideOut(tween(300)) {
    IntOffset(it.width, 0)
} + fadeOut(tween(200))

@Composable
fun FutureGuiderNavHost() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onGetStarted = { navController.navigate(Routes.REGISTER) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }},
                onLogin = { navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }},
                onAutoLogin = {
                    navController.navigate(Routes.EDUCATION) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },
                isLoggedIn = isLoggedIn
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                viewModel = authViewModel,
                onSuccess = {
                    navController.navigate(Routes.EDUCATION) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate(Routes.LOGIN) }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = authViewModel,
                onSuccess = {
                    navController.navigate(Routes.EDUCATION) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.EDUCATION) {
            EducationSelectionScreen(
                onSelect = { id, name ->
                    navController.navigate(Routes.careerTree(id, name))
                },
                onSavedPaths = { navController.navigate(Routes.SAVED_PATHS) },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.SPLASH) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.CAREER_TREE,
            arguments = listOf(
                navArgument("rootNodeId") { type = NavType.IntType },
                navArgument("rootNodeName") { type = NavType.StringType }
            )
        ) { back ->
            val rootId   = back.arguments?.getInt("rootNodeId") ?: 0
            val rootName = back.arguments?.getString("rootNodeName")?.decode() ?: ""
            CareerTreeScreen(
                rootNodeId = rootId,
                rootNodeName = rootName,
                onNodeSelected = { id, name ->
                    navController.navigate(Routes.careerDetail(id, name))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.CAREER_DETAIL,
            arguments = listOf(
                navArgument("nodeId") { type = NavType.IntType },
                navArgument("nodeName") { type = NavType.StringType }
            )
        ) {
            CareerDetailScreen(
                onBack = { navController.popBackStack() },
                onSavedPaths = { navController.navigate(Routes.SAVED_PATHS) }
            )
        }

        composable(Routes.SAVED_PATHS) {
            SavedPathsScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.SPLASH) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}

private fun String.decode() = java.net.URLDecoder.decode(this, "UTF-8")
