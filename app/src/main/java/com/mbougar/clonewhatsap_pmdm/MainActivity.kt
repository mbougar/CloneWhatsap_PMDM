package com.mbougar.clonewhatsap_pmdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mbougar.clonewhatsap_pmdm.model.Contact
import com.mbougar.clonewhatsap_pmdm.model.Message
import com.mbougar.clonewhatsap_pmdm.navigation.AppNavigation
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
                    val navController = rememberNavController()
                    AppNavigation(navController = navController, innerPadding)
                }
            }
        }
    }
}

//val contacts = mutableListOf<Contact>(
//    Contact("Karim", R.drawable.profile_pic_karim, "Ya nos veremos", Date(2024, 10, 15).time, listOf(
//        Message(
//            nombreUsuario = "Me",
//            textoMensaje = "Hey! How are you?",
//            mensajeLeido = true
//        ),
//        Message(
//            nombreUsuario = "Karim",
//            textoMensaje = "I'm good, thanks! What about you?",
//            mensajeLeido = true
//        ),
//        Message(
//            nombreUsuario = "Me",
//            textoMensaje = "All good here. Did you check out the new movie?",
//            mensajeLeido = true
//        ),
//        Message(
//            nombreUsuario = "Karim",
//            textoMensaje = "Not yet. Is it worth watching?",
//            mensajeLeido = false
//        ),
//        Message(
//            nombreUsuario = "Me",
//            textoMensaje = "Definitely! You’ll love it.",
//            mensajeLeido = false
//        ),
//        Message(
//            nombreUsuario = "Karim",
//            textoMensaje = "Alright, I'll watch it tonight.",
//            mensajeLeido = false
//        ),
//        Message(
//            nombreUsuario = "Me",
//            textoMensaje = "Let me know what you think. Enjoy!",
//            mensajeLeido = false
//        )
//    )),
//    Contact("Marta", R.drawable.profile_pic_marta, "Que va", Date(2024, 6, 12).time, listOf<Message>()),
//    Contact("Ana", R.drawable.profile_pic_ana, "Si", Date(2024, 8, 19).time, listOf<Message>()),
//    Contact("Antonio", R.drawable.profile_pic_antonio, "Tenemos que hablar", Date(2024, 2, 11).time, listOf<Message>()),
//    Contact("Carlos", R.drawable.profile_pic_carlos, "Creo que Antonio quiere hablar contigo", Date(2023, 11, 26).time, listOf<Message>()),
//    Contact("Marco", R.drawable.profile_pic_marco, "luego te digo", Date(2024, 3, 16).time, listOf<Message>()),
//    Contact("Pedro", R.drawable.profile_pic_pedro, "sales hoy??", Date(2024, 9, 10).time, listOf<Message>()),
//    Contact("Pepe", R.drawable.profile_pic_pepe, "k", Date(2024, 3, 2).time, listOf<Message>()),
//    Contact("Juan", R.drawable.profile_pic_unknown, "¿Cómo estás?", Date(2024, 10, 25).time, listOf<Message>()),
//    Contact("Lucía", R.drawable.profile_pic_unknown, "Hablamos mañana", Date(2024, 7, 14).time, listOf<Message>()),
//    Contact("José", R.drawable.profile_pic_unknown, "Nos vemos pronto", Date(2024, 9, 30).time, listOf<Message>()),
//    Contact("Isabel", R.drawable.profile_pic_unknown, "Tengo noticias", Date(2024, 5, 10).time, listOf<Message>()),
//    Contact("Francisco", R.drawable.profile_pic_unknown, "Ya te cuento todo", Date(2024, 4, 22).time, listOf<Message>()),
//    Contact("Elena", R.drawable.profile_pic_unknown, "Estaré esperando", Date(2024, 3, 5).time, listOf<Message>()),
//    Contact("Pedro", R.drawable.profile_pic_unknown, "Tengo una propuesta", Date(2024, 11, 2).time, listOf<Message>()),
//    Contact("María", R.drawable.profile_pic_unknown, "Te llamo más tarde", Date(2024, 8, 1).time, listOf<Message>()),
//    Contact("Raúl", R.drawable.profile_pic_unknown, "¿Vienes este fin de semana?", Date(2024, 6, 18).time, listOf<Message>()),
//    Contact("Sofía", R.drawable.profile_pic_unknown, "Nos vemos el lunes", Date(2024, 2, 9).time, listOf<Message>())
//).sortedByDescending { it.fechaUltimoMensaje }

val contacts = mutableListOf<Contact>(
    Contact("Karim", R.drawable.profile_pic_karim, "Ya nos veremos", Date(2024, 10, 15).time),
    Contact("Marta", R.drawable.profile_pic_marta, "Que va", Date(2024, 6, 12).time),
    Contact("Ana", R.drawable.profile_pic_ana, "Si", Date(2024, 8, 19).time),
    Contact("Antonio", R.drawable.profile_pic_antonio, "Tenemos que hablar", Date(2024, 2, 11).time),
    Contact("Carlos", R.drawable.profile_pic_carlos, "Creo que Antonio quiere hablar contigo", Date(2023, 11, 26).time),
    Contact("Marco", R.drawable.profile_pic_marco, "luego te digo", Date(2024, 3, 16).time),
    Contact("Pedro", R.drawable.profile_pic_pedro, "sales hoy??", Date(2024, 9, 10).time),
    Contact("Pepe", R.drawable.profile_pic_pepe, "k", Date(2024, 3, 2).time),
    Contact("Juan", R.drawable.profile_pic_unknown, "¿Cómo estás?", Date(2024, 10, 25).time),
    Contact("Lucía", R.drawable.profile_pic_unknown, "Hablamos mañana", Date(2024, 7, 14).time),
    Contact("José", R.drawable.profile_pic_unknown, "Nos vemos pronto", Date(2024, 9, 30).time),
    Contact("Isabel", R.drawable.profile_pic_unknown, "Tengo noticias", Date(2024, 5, 10).time),
    Contact("Francisco", R.drawable.profile_pic_unknown, "Ya te cuento todo", Date(2024, 4, 22).time),
    Contact("Elena", R.drawable.profile_pic_unknown, "Estaré esperando", Date(2024, 3, 5).time),
    Contact("Pedro", R.drawable.profile_pic_unknown, "Tengo una propuesta", Date(2024, 11, 2).time),
    Contact("María", R.drawable.profile_pic_unknown, "Te llamo más tarde", Date(2024, 8, 1).time),
    Contact("Raúl", R.drawable.profile_pic_unknown, "¿Vienes este fin de semana?", Date(2024, 6, 18).time),
    Contact("Sofía", R.drawable.profile_pic_unknown, "Nos vemos el lunes", Date(2024, 2, 9).time)
).sortedByDescending { it.fechaUltimoMensaje }