package com.mbougar.clonewhatsap_pmdm.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.mbougar.clonewhatsap_pmdm.model.Contact
import com.mbougar.clonewhatsap_pmdm.ui.screens.ChatScreen
import com.mbougar.clonewhatsap_pmdm.ui.screens.MainScreen

/**
 * https://developer.android.com/develop/ui/compose/navigation?hl=es-419
 * **/
@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController, innerPadding) }
        composable<Contact> { backStackEntry ->
            val contact: Contact = backStackEntry.toRoute()
            ChatScreen(navController, innerPadding, contact = contact)
        }
    }
}