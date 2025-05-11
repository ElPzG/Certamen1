object RepositorioEmpresa {

    // lista mutable de empresas
    private val empresas = mutableListOf<Empresa>()

    // esta func. me devuelve todas las empresas
    fun getAll() = empresas

    // esta func. agrega una empresa nueva a empresas
    fun create(e: Empresa) = empresas.add(e)

    // esta func. me devuelve la empresa segun el id del empleado
    fun getByEmpleadoId(id: Int) = empresas.filter {it.empleadoId ==id}

    // esta func. me devuelve la empresa segun el nombree del empleado
    fun getByNombre(nombre: String) = empresas.filter { it.nombre.equals(nombre, ignoreCase = true) }
}