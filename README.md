# Certamen 1 TEL335 

En este proyecto se desarrolla una API REST a traves de KMP. 

## 1) 

En la primera sección se implementó un CRUD completo en memoria de la entidad "Empleado" (ya que correspondía a mi verificador del RUT 6). 
Este tiene los siguientes atributos:

- id
- nombre
- apellido
- cargo
- nacionalidad

Con lo anterior y utilizando algunos datos cargados en memoria, se tienen las siguientes peticiones disponibles, las cuales pueden realizarse a través de la aplicación "Postman" o también a través de la terminal a través de curl.exe:

- GET http://127.0.0.1:8080/empleados
- GET http://127.0.0.1:8080/empleados/{id}
- POST http://127.0.0.1:8080/empleados + los datos del empleado en el caso de Postman
- PUT http://127.0.0.1:8080/empleados + los datos del empleado en el caso de Postman
- DELETE http://127.0.0.1:8080/empleados/{id}

## 2) 

En esta sección, se implementó la entidad relacionada "Empresa", la cual está relacionada con empleado. Estas están precargadas en memoria, ya que solo piden peticiones GET. En este caso, los atributos de la empresa son: 

- id
- nombre
- area
- empleadoId

En este caso, como solicitaban buscar por filtro, se desarrollaron tres tipos de busqueda según los mismos atributos de las empresas: 

- GET http://127.0.0.1:8080/empleados/empresas
- GET http://127.0.0.1:8080/empleados/empresas/id
- GET http://127.0.0.1:8080/empleados/empresas/nombre
- GET http://127.0.0.1:8080/empleados/empresas/area

Luego, para correr el servidor, en el caso de utilizar Android Studio, debe ejecutar Application.kt la cual se encuentra en: server/src/main/kotlin/cl/usm/tel335/Application.kt 


  
