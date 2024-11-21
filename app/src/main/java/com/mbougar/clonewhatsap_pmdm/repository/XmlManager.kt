package com.mbougar.clonewhatsap_pmdm.repository

import com.mbougar.clonewhatsap_pmdm.model.Message
import org.w3c.dom.*
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * Clase de utilidad para generar y modificar archivos XML
 **/
class XmlManager {

    fun crearXML(rutaFichero: Path, messages: List<Message>) {

        val dbf: DocumentBuilderFactory
        val builder: DocumentBuilder
        val imp: DOMImplementation
        val document: Document

        try {
            dbf = DocumentBuilderFactory.newInstance()
            builder = dbf.newDocumentBuilder()
            imp = builder.domImplementation
            document = imp.createDocument(null, "messages", null)
        } catch (_: Exception) {
            return
        }

        for (message in messages) {
            val messageNodo: Element = document.createElement("message")
            document.documentElement.appendChild(messageNodo)
            messageNodo.setAttribute("id", message.id.toString())

            val nombreUsuario: Element = document.createElement("apellido")
            val textoMensaje: Element = document.createElement("departamento")
            val mensajeLeido: Element = document.createElement("salario")

            val textoNombreUsuario: Text = document.createTextNode(message.nombreUsuario)
            val textoTextoMensaje: Text = document.createTextNode(message.textoMensaje)
            val textoMensajeLeido: Text = document.createTextNode(message.mensajeLeido.toString())

            nombreUsuario.appendChild(textoNombreUsuario)
            textoMensaje.appendChild(textoTextoMensaje)
            mensajeLeido.appendChild(textoMensajeLeido)

            messageNodo.appendChild(nombreUsuario)
            messageNodo.appendChild(textoMensaje)
            messageNodo.appendChild(mensajeLeido)
        }

        val source: Source = DOMSource(document)
        val result: StreamResult

        try {
            result = StreamResult(rutaFichero.toFile())
        } catch (_: Exception) {
            return
        }

        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)
    }

    fun leerXML(rutaFichero: Path): List<Message> {

        val dbf: DocumentBuilderFactory
        val builder: DocumentBuilder
        val document: Document
        val messages = mutableListOf<Message>()

        try {
            dbf = DocumentBuilderFactory.newInstance()
            builder = dbf.newDocumentBuilder()
            document = builder.parse(rutaFichero.toFile())
        } catch (_: Exception) {
            return messages
        }

        val root: Element = document.documentElement
        root.normalize()

        val listaNodos: NodeList = root.getElementsByTagName("message")

        for (i in 0..< listaNodos.length) {

            val nodo = listaNodos.item(i)

            if (nodo.nodeType == Node.ELEMENT_NODE) {

                val nododElemento = nodo as Element

                val elementoId = nododElemento.getAttribute("id")
                val elementoNombreUsuario = nododElemento.getElementsByTagName("nombreUsuario")
                val elementoTextoMensaje = nododElemento.getElementsByTagName("textoMensaje")
                val elementoMensajeLeido = nododElemento.getElementsByTagName("mensajeLeido")

                val message = Message(
                    id = elementoId.toInt(),
                    nombreUsuario = elementoNombreUsuario.item(0).textContent,
                    textoMensaje = elementoTextoMensaje.item(0).textContent,
                    mensajeLeido = elementoMensajeLeido.item(0).textContent.toBoolean()
                )

                messages.add(message)
            }
        }

        return messages
    }
}