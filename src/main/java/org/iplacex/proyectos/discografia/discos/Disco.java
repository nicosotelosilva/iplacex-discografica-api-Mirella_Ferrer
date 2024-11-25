package org.iplacex.proyectos.discografia.discos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discos")

public class Disco {

    @Id
    public String id;
    public String idArtista; 
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;

    public Disco(String idArtista, String nombre, int anioLanzamiento, List<String> canciones) {
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.anioLanzamiento = anioLanzamiento;
        this.canciones = canciones;
    }

        public String getId() {
            return id;
        }
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getIdArtista() {
            return idArtista;
        }
    
        public void setIdArtista(String idArtista) {
            this.idArtista = idArtista;
        }
    
        public String getNombre()
     {
            return nombre;
        }
    
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    
        public int getAnioLanzamiento() {
            return anioLanzamiento;
        }
    
        public void setAnioLanzamiento(int anioLanzamiento) {
            this.anioLanzamiento = anioLanzamiento;
    
        }
    
        public List<String> getCanciones() {
            return canciones;
        }
    
        public void setCanciones(List<String> canciones) {
            this.canciones = canciones;
    
        }
    }

