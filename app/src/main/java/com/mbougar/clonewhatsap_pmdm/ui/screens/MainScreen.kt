package com.mbougar.clonewhatsap_pmdm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.mbougar.clonewhatsap_pmdm.model.Contact
import com.mbougar.clonewhatsap_pmdm.R
import com.mbougar.clonewhatsap_pmdm.contacts
import com.mbougar.clonewhatsap_pmdm.ui.theme.blackBackground
import java.util.Date

@Composable
fun MainScreen(navController: NavHostController, innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Header()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            items(contacts) { contacto ->
                BannerContacto(contacto) { navController.navigate(route = contacto) }
            }
        }
    }
}

@Composable
fun BannerContacto(contact: Contact, onNavigateToChat: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val fecha = Date(contact.fechaUltimoMensaje)
    val fechaString = fecha.day.toString() + "/" + fecha.month.toString() + "/" + fecha.year.toString()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(12.dp)
            .clickable { onNavigateToChat() }
    ) {
        Image(
            painter = painterResource(id = contact.imageRes),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .clickable { showDialog = true },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = contact.nombre,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = contact.ultimoMensaje.take(8) + "...",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = fechaString,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }

    if (showDialog) {
        ContactDialog(contact = contact, onDismiss = { showDialog = false })
    }
}

@Composable
fun ContactDialog(contact: Contact, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .width(300.dp)
                .height(400.dp),
            color = Color.Black.copy(alpha = (0f))
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = painterResource(id = contact.imageRes),
                        contentDescription = "Imagen de perfil",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(0.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = contact.nombre,
                        style = TextStyle(fontSize = 20.sp),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .background(Color.Black.copy(alpha = 0.4f))
                            .clip(RoundedCornerShape(0.dp))
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(8.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(blackBackground)
                ) {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Filled.Message, contentDescription = "Message")
                    }
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Filled.Call, contentDescription = "Call")
                    }
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Filled.Videocam, contentDescription = "Video Call")
                    }
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More Options")
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name_in_app),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Outlined.CameraAlt, contentDescription = "Camera")
            }

            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }

            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More options")
            }
        }
    }
}