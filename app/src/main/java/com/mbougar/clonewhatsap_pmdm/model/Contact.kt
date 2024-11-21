package com.mbougar.clonewhatsap_pmdm.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val nombre: String,
    val imageRes: Int,
    var ultimoMensaje: String,
    var fechaUltimoMensaje: Long
)