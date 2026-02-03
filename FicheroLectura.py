try:
    fichero=open("quijote.txt","r+")
    print(fichero.readline())
    print("Desues de leer estoy en: ",fichero.tell())
    fichero.write("XXX")
    print("Desues de escribir estoy en: ",fichero.tell())
    fichero.seek(0)
    print("Desues de mover el cursor a 0 estoy en: ",fichero.tell())
    fichero.close()
except:
    print("Error al leer el fichero")