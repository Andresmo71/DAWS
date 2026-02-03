import mysql.connector

try:
    conexion=mysql.connector.connect(user='daw2',password='LaElipa',host='localhost',database='dwes7')

    cursor=conexion.cursor()
    #query=("SELECT * FROM pokemon")
    #cursor.execute(query)
    #metodo 1
    #for fila in cursor:
    #    print(fila)


    #metodo 2
    #lista=cursor.fetchall()
    #print("Hay ", len(lista),"pokemons")
    #print(lista)


    #metodo 3
    #query2="SELECT nombre, numero_pokedex FROM pokemon"pokemon
    #cursor.execute(query2)
    #for(pokemon,id) in cursor:
    #    print(id,"-",pokemon)


    #metodo 4
    query4="UPDATE pokemon SET nombre='Pokemon Cachas' WHERE nombre='Mewtwo'"
    cursor.execute(query4)
    print(cursor.rowcount," Filas afectadas por el query")
    query5="SELECT * FROM pokemon"
    cursor.execute(query5)
    for fila in cursor:
        print(fila)
    conexion.commit()
    cursor.close()
    conexion.close()


except mysql.connector.Error as err:
    print(err)