package com.mbougar.clonewhatsap_pmdm.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val nombreUsuario: String,
    var textoMensaje: String,
    var mensajeLeido: Boolean = false
)