package com.mbougar.clonewhatsap_pmdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
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
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme()
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Header()

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            val contacts = mutableListOf<Contacto>(
                                Contacto("Karim", R.drawable.profile_pic_karim, "Ya nos veremos", Date(2024, 10, 15)),
                                Contacto("Marta", R.drawable.profile_pic_marta, "Que va", Date(2024, 6, 12)),
                                Contacto("Ana", R.drawable.profile_pic_ana, "Si", Date(2024, 8, 19)),
                                Contacto("Antonio", R.drawable.profile_pic_antonio, "Tenemos que hablar", Date(2024, 2, 11)),
                                Contacto("Carlos", R.drawable.profile_pic_carlos, "Creo que Antonio quiere hablar contigo", Date(2023, 11, 26)),
                                Contacto("Marco", R.drawable.profile_pic_marco, "luego te digo", Date(2024, 3, 16)),
                                Contacto("Pedro", R.drawable.profile_pic_pedro, "sales hoy??", Date(2024, 9, 10)),
                                Contacto("Pepe", R.drawable.profile_pic_pepe, "k", Date(2024, 3, 2)),
                                Contacto("Juan", R.drawable.profile_pic_unknown, "¿Cómo estás?", Date(2024, 10, 25)),
                                Contacto("Lucía", R.drawable.profile_pic_unknown, "Hablamos mañana", Date(2024, 7, 14)),
                                Contacto("José", R.drawable.profile_pic_unknown, "Nos vemos pronto", Date(2024, 9, 30)),
                                Contacto("Isabel", R.drawable.profile_pic_unknown, "Tengo noticias", Date(2024, 5, 10)),
                                Contacto("Francisco", R.drawable.profile_pic_unknown, "Ya te cuento todo", Date(2024, 4, 22)),
                                Contacto("Elena", R.drawable.profile_pic_unknown, "Estaré esperando", Date(2024, 3, 5)),
                                Contacto("Pedro", R.drawable.profile_pic_unknown, "Tengo una propuesta", Date(2024, 11, 2)),
                                Contacto("María", R.drawable.profile_pic_unknown, "Te llamo más tarde", Date(2024, 8, 1)),
                                Contacto("Raúl", R.drawable.profile_pic_unknown, "¿Vienes este fin de semana?", Date(2024, 6, 18)),
                                Contacto("Sofía", R.drawable.profile_pic_unknown, "Nos vemos el lunes", Date(2024, 2, 9))
                            ).sortedByDescending { it.fechaUltimoMensaje }

                            items(contacts) { contacto ->
                                BannerContacto(contacto)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BannerContacto(contacto: Contacto) {
    var showDialog by remember { mutableStateOf(false) }
    val fecha = contacto.fechaUltimoMensaje.day.toString() + "/" + contacto.fechaUltimoMensaje.month.toString() + "/" + contacto.fechaUltimoMensaje.year.toString()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = contacto.imageRes),
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
                text = contacto.nombre,
                fontWeight = FontWeight.Bold
                )
            Text(
                text = contacto.ultimoMensaje.take(8) + "...",
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
                text = fecha,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }

    if (showDialog) {
        ContactDialog(contacto = contacto, onDismiss = { showDialog = false })
    }
}

@Composable
fun ContactDialog(contacto: Contacto, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            color = Color.Black.copy(alpha = (0f))
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    Image(
                        painter = painterResource(id = contacto.imageRes),
                        contentDescription = "Imagen de perfil",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(0.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = contacto.nombre,
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
                        .background(Color.Black)
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