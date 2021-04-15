/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static com.coti.tools.OpMat.importFromDisk;
import com.coti.tools.Rutas;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Usuario
 */
public class Playlist implements Serializable{

   private  String nombre;
   private  ArrayList <Song> canciones;

   Musicfy ms = new Musicfy();
   
    public Playlist(String nombre, ArrayList <Song> canciones) {
        this.nombre = nombre;
        this.canciones = canciones;
    }
    Playlist(){
     this.nombre="";
     this.canciones=null;
    }
    
    public static Playlist crearPlaylist(String nombre,ArrayList <Song> canciones) {
        return new Playlist(nombre,canciones);
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }
    
    public  Playlist crearPlaylistAleatorio() throws Exception{
         Random r = new Random();
         String[][] playlist_aleatorio = importarAleatorio();
         ArrayList <Song> canciones_aleatoria=new ArrayList<Song>();
         
         int i1 = r.nextInt(playlist_aleatorio.length);
         int i2 = 1+r.nextInt(9);
         int i3,i4,i5;
         
         Song cancion_nueva=new Song();
         
          for(int i=0;i<i2;i++){
              cancion_nueva=cancion_nueva.crearCancionAleatorio();
              canciones_aleatoria.add(cancion_nueva);
          }
         
         Playlist playlist_aleatoria=Playlist.crearPlaylist(playlist_aleatorio[i1][0],canciones_aleatoria);
         
         

         
        return playlist_aleatoria;
    }
    public static String[][] importarAleatorio() throws Exception{
        String[][] playlist_aleatorio = null;
         
        Path path_cancion=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"aleatorios", "nombresPlaylists.txt");
        playlist_aleatorio=importFromDisk(path_cancion.toFile(),"\t");
        
        return playlist_aleatorio;
    }

    public String Formato() {
        Song c = new Song();
        String listcancion = String.join(", ", c.FormatoSong(canciones));
        return String.format("|%s|%s|\n",nombre,listcancion);     
    }
    public String FormatoCanciones() {
        Song c = new Song();
        String listcancion = String.join(", ", c.FormatoSong(canciones));
        return String.format("|%s|\n",listcancion);     
    }
} 
