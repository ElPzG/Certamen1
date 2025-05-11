import kotlinx.serialization.Serializable

@Serializable
//La entidad relacionada que seleccione es la empresa, ya que obviamente un empleado trabaja en una empresa
data class Empresa(
    val id: Int,
    val nombre: String,
    val area: String,
    val empleadoId: Int
)
