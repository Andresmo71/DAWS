try:
    fichero=open("quijote.txt")
    #texto=fichero.readlines()
    #print(texto)
    linea=fichero.readline()
    while linea!="":
        if linea[-1]=="\n":
            print(linea[:-1])
        else:
            print(linea)
        linea=fichero.readline()
    fichero.close()
except:
    print("Error al leer el fichero")
