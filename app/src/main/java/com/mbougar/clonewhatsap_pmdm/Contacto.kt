package com.mbougar.clonewhatsap_pmdm

import java.util.Date

data class Contacto(
    val nombre: String,
    val imageRes: Int,
    var ultimoMensaje: String,
    var fechaUltimoMensaje: Date
)