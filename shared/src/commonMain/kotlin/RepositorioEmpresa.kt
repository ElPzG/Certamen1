object RepositorioEmpresa {

    // lista mutable de empresas
    private val empresas = mutableListOf(
        Empresa(id = 1, nombre = "IBM", area = "Tecnologia" ,empleadoId = 1),
        Empresa(id = 2, nombre = "Huawei", area = "Tecnologia" ,empleadoId = 3),
        Empresa(id = 3, nombre = "LimpiaMax", area = "Limpieza" ,empleadoId = 2),
    )

    // esta func. me devuelve todas las empresas
    fun getAll() = empresas

    // esta func. me devuelve la empresa segun el id de la empresa
    fun getById(id: Int): Empresa? = empresas.find {it.id == id}

    // esta func. me devuelve la empresa segun el nombree del empleado
    fun getByNombre(nombre: String) = empresas.filter { it.nombre.equals(nombre, ignoreCase = true) }

    // esta func. me devuelve la empresa segun el area
    fun getByArea(area: String) =
        empresas.filter {it.area.equals(area, ignoreCase = true)}
}