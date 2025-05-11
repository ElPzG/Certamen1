object RepositorioEmpleado{

    // lista mutable para guardar los empleados
    private val empleados = mutableListOf<Empleado>()

    // esta func. me devuelve todos los empleados
    fun getAll() = empleados

    // esta func. busca al empleado por su id
    fun getById(id: Int): Empleado? = empleados.find { it.id == id }

    // esta func. agrega un nuevo empleado a empleados
    fun create(e: Empleado) = empleados.add(e)

    // con esta func. actualizo a un empleado que ya existe si cambio alguno de sus atributos
    fun update(id:Int, nuevo: Empleado): Boolean{
        val index = empleados.indexOfFirst{it.id == id}
        return if (index != -1){
            empleados[index] = nuevo
            true
        }else false
    }

    // esta func. elimina un empleado por su id
    fun delete(id: Int): Boolean{
        val empleado = getById(id)
        return empleado?.let{empleados.remove(it)} ?: false
    }
}