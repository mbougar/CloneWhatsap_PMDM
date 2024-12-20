package com.mbougar.clonewhatsap_pmdm.ui.screens

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mbougar.clonewhatsap_pmdm.contactMessagesMap
import com.mbougar.clonewhatsap_pmdm.model.Contact
import com.mbougar.clonewhatsap_pmdm.model.Message
import com.mbougar.clonewhatsap_pmdm.ui.theme.blackBackground
import com.mbougar.clonewhatsap_pmdm.ui.theme.messageBlack
import com.mbougar.clonewhatsap_pmdm.ui.theme.messageGreen
import com.mbougar.clonewhatsap_pmdm.ui.theme.readMessageColor
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date
import kotlin.io.path.Path

@Composable
fun ChatScreen(navController: NavHostController, innerPadding: PaddingValues, contact: Contact) {

    var messages = contactMessagesMap[contact.nombre]

    if (messages == null) {
        messages = mutableListOf()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .imePadding()
    ) {
        Scaffold(
            topBar = {
                HeaderChat(contact) { navController.navigate("main") }
            },
            bottomBar = { ChatInput2 { string ->
                contactMessagesMap[contact.nombre]?.add(Message(
                    id = messages.size + 1,
                    nombreUsuario = "Me",
                    textoMensaje = string,
                    mensajeLeido = false,
                    fechaMensaje = Date().time
                ))
            } },
            content = { innerPadding ->
                Column(
                    Modifier.padding(innerPadding)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        items(messages) { message ->
                            MessageChat(message, message.nombreUsuario != contact.nombre)
                        }
                    }
                }
            }
        )
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
                .clickable { },
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
    val fecha = Date(message.fechaMensaje)
    val horaString = "%02d : %02d".format(fecha.hours, fecha.minutes)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isSentByUser) messageGreen else messageBlack,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp)
                .widthIn(max = 300.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = message.textoMensaje,
                    color = Color.White,
                    fontSize = 14.sp
                )
                if (isSentByUser) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End) {
                        Text(
                            text = horaString,
                            color = Color.Gray,
                            fontSize = 10.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = if (message.mensajeLeido) Icons.Filled.DoneAll else Icons.Filled.Done,
                            contentDescription = "Message Status",
                            tint = if (message.mensajeLeido) readMessageColor else Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatInput(onSend: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText: String -> text = newText },
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFF262D31)),
            placeholder = { Text("Escribe un mensaje") },
            textStyle = TextStyle(color = Color.White)
        )
        IconButton(onClick = {
            if (text.isNotBlank()) {
                onSend(text)
                text = ""
            }
        }) {
            if (text != "" ) {
                Icon(Icons.Default.Send, contentDescription = "Enviar", tint = Color(0xFF128C7E))
            } else {
                Icon(Icons.Default.Mic, contentDescription = "Micro", tint = Color(0xFF128C7E))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInput2(onSend: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val shape = MaterialTheme.shapes.small.copy(CornerSize(50.dp))

        TextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            value = text,
            onValueChange = { newText -> text = newText },
            shape = shape,
            leadingIcon = {
                IconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Gray,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.EmojiEmotions,
                        contentDescription = "Emoji"
                    )
                }
            },
            trailingIcon = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    IconButton(
                        onClick = { },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.Gray,
                            containerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AttachFile,
                            contentDescription = "Attach File"
                        )
                    }
                    if (text.isBlank()) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(28.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color.Gray,
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CameraAlt,
                                contentDescription = "Camera"
                            )
                        }
                    }
                }
            },
            placeholder = { Text("Type a message", color = Color.Gray) },
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF262D31),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        FloatingActionButton(
            onClick = {
                if (text.isNotBlank()) {
                    onSend(text)
                    text = ""
                }
            },
            modifier = Modifier
                .padding(end = 16.dp)
                .wrapContentSize(),
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            containerColor = messageGreen,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(
                imageVector = if (text.isNotBlank()) Icons.Outlined.Send else Icons.Outlined.Mic,
                contentDescription = if (text.isNotBlank()) "Send Message" else "Record Audio"
            )
        }
    }
}