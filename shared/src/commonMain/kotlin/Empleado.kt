import kotlinx.serialization.Serializable

@Serializable
//Por mi RUT, me toco la entidad empleado
data class Empleado(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val cargo: String,
    val nacionalidad: String
)
