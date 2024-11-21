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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mbougar.clonewhatsap_pmdm.model.Contact
import com.mbougar.clonewhatsap_pmdm.model.Message
import java.util.Date

@Composable
fun ChatScreen(navController: NavHostController, innerPadding: PaddingValues, contact: Contact) {
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        HeaderChat(contact) { navController.navigate("main") }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            reverseLayout = true
        ) {

//            items(contact.messages) { message ->
//                MessageChat(message, message.nombreUsuario != contact.nombre)
//            }
        }
    }
}

@Composable
fun HeaderChat(contact: Contact, onClickBack: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        val fecha = Date(contact.fechaUltimoMensaje)
        val fechaString = fecha.day.toString() + "/" + fecha.month.toString() + "/" + fecha.year.toString()

        IconButton(onClick = { onClickBack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
        }

        Image(
            painter = painterResource(id = contact.imageRes),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .clickable {  },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.clickable {  } //Abre la información del chat con el contacto (fotos enviadas, etc)
        ) {
            Text(
                text = contact.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = "Last seen $fechaString",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Outlined.Videocam, contentDescription = "VideoCall")
            }

            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.Call, contentDescription = "Call")
            }

            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More options")
            }
        }

    }
}

@Composable
fun MessageChat(message: Message, isSentByUser: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isSentByUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        ) {
            Column {
                if (!isSentByUser) {
                    Text(
                        text = message.nombreUsuario,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                Text(
                    text = message.textoMensaje,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp
                )
                if (isSentByUser) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = if (message.mensajeLeido) Icons.Filled.DoneAll else Icons.Filled.Done,
                            contentDescription = "Message Status",
                            tint = if (message.mensajeLeido) Color.Blue else Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Sent", // You could include a timestamp here
                            color = Color.Gray,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}