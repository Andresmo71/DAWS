import pickle
class Persons:
    def __init__(self,nombre):
        self.__nombre = nombre

    @property
    def nombre(self):
        return self.__nombre


person1 = Persons("Jose Andres Marañon")

person2 = Persons("Roberto")



try:
    #fichero = open("binario.bin","wb")
    #pickle.dump(person1,fichero)
    #pickle.dump(person2,fichero)
    # -----------------------------------
    #fichero=open("binario.bin","rb")
    #p1=pickle.load(fichero)
    #p2=pickle.load(fichero)
    #print(p1.nombre)
    #print(p2.nombre)
    #fichero.close()
    #---------------------------------------
    fichero=open("binario.bin","wb")
    lista=[]
    lista.append(person1)
    lista.append(person2)
    pickle.dump(lista,fichero)
    fichero.close()
    fichero=open("binario.bin","rb")
    l=pickle.load(fichero)
    print(l[1].nombre)
    for elemento in l:
        print(elemento.nombre)
    fichero.close()




except:
    print("Error al leer el fichero")