try:
    fichero=open("quijote.txt","wt")
    #lista=["En lugar de Leon \ncuyo apellido \nsi quiero acordarme"]
    lista=["1","2","3","4","5","6"] #Solo acepta strings por lo que da error
    fichero.writelines(lista)
    #fichero.write("En un lugar de Leon\n")
    #fichero.write("cuyo apellido\n")
    #fichero.write("si quiero acordarme...\n")

    fichero.close()
except:
    print("Error al leer el fichero")