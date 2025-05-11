package cl.usm.tel335

import Empleado
import Empresa
import RepositorioEmpleado
import RepositorioEmpresa
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*
import io.ktor.server.request.*
import io.ktor.http.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation){
        json(Json{
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    routing {
        get("/"){
            call.respondText("Server activo")
        }
        // Mi RUT tiene como ultimo digito el 6, por lo tanto la entidad correspondiente es "empleado"
        route("/empleados") {
            //me dan todos los empleados
            get {
                call.respond(RepositorioEmpleado.getAll())
            }

            // devuelve el empleado por su id
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val empleado = id?.let { RepositorioEmpleado.getById(it) }
                if (empleado != null) {
                    call.respond(empleado)
                }
                else {
                    call.respond(HttpStatusCode.NotFound, "No se encuentra al empleado.")
                }
            }

            // creo un nuevo empleado
            post {
                val nuevo = call.receive<Empleado>()
                val existente = RepositorioEmpleado.getById(nuevo.id)
                if (existente == null) {
                    RepositorioEmpleado.create(nuevo)
                    call.respond(HttpStatusCode.Created, "Empleado creado.")
                } else {
                    call.respond(HttpStatusCode.Conflict, "Empleado con ID ${nuevo.id} ya existe.")
                }
            }

            // actualizo un empleado al realizarle un cambio en sus atributos
            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val actualizado = call.receive<Empleado>()
                if (id != null && RepositorioEmpleado.update(id, actualizado)){
                    call.respond(HttpStatusCode.OK, "Empleado actualizado")
                }
                else {
                    call.respond(HttpStatusCode.NotFound, "No se encuentra al empleado con ID $id ")
                }
            }

            // borro un empleado segun su id
            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id != null && RepositorioEmpleado.delete(id)) {
                    call.respond(HttpStatusCode.NoContent, "Empleado con ID $id borrado. ")
                }
                else{
                    call.respond(HttpStatusCode.NotFound, "No se encuentra al empleado con ID $id .")
                }
            }

            //Aca la seccion de la entidad relacionada. En este caso elegi empresa
            route("/empresas") {
                // me devuelve todas las empresas
                get {
                    call.respond(RepositorioEmpresa.getAll())
                }

                // me entrega una empresa segun el filtro, es decir por nombre o por el id del empleado asociado
                get("/{filtro}") {
                    val filtro = call.parameters["filtro"]
                    val resultado = filtro?.toIntOrNull()?.let {
                        RepositorioEmpresa.getById(it)?.let { listOf(it)} ?: emptyList()
                    } ?: (
                            RepositorioEmpresa.getByNombre(filtro ?: "") +
                            RepositorioEmpresa.getByArea(filtro ?: "")
                            )
                    if(resultado.isEmpty()){
                        call.respond(HttpStatusCode.NotFound, "No hay empresas con ese filtro de busqueda.")
                    }
                    else{
                        call.respond(resultado)
                    }
                }
            }
        }
    }
}