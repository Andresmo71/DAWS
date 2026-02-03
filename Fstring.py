nombre="Andres"
edad=20
sueldo=1000.556
print("hola soy %s ,tengo %d años y cobro %.2f euros al mes"%(nombre,edad,1000.55))
print(f"hola soy {nombre} ,tengo {edad} años y cobro {sueldo:.2f} euros al mes")
print(f"hola soy %s ,tengo %d años y cobro {sueldo} euros al mes"%(nombre,edad)) #mezclado

ratio=0.08394
print(f"porcentaje: {ratio:.2%}") #calculara porcentaje ,aqui tambien truncamos a dos decimales


habitantes=7123456789
print(f"Poblacion: {habitantes:,} habitantes" )


num1=45
num2=123
print(f"{num1:4d}\n{num2:4d}" ) #Da 4 espacios al numero , util para mostrar en columnas , si pones 04d te rellena esos espacios con 0


texto="Python"
print(f"***{texto:<20}***")
print(f"***{texto:>20}***")
print(f"***{texto:^20}***") #Dedica los espacios indicados y hacia donde mueve la variable(izq,der,centrado)

print(f"{num1=}\n{num2=}")#Mostrar variable y su valor

texto=f"{num1=}\n{num2=}" #Se puede obviamente asignar a una variable un f string
print(texto)


def devuelveNombre():

    return "Andres Marañon"

ficha=f"""
============
Nombre:{devuelveNombre()}
Edad:{edad}
Sueldo: {sueldo:.2f} {{euros}}
"""
print(ficha)
print("hola")

print(f"num1 es par? {True if num1%2==0 else False}")#Condicion dentro


num1=5
print(f"Valoracion: {'Alto' if num1 > 50 else 'Medio' if num1>25 else 'bajo'}")  #Condicion anidada. Orden:1.Lo que devuelve 2.Condicion 3.else 1.Lo que devuelve 2.Condicion 3.Else
















