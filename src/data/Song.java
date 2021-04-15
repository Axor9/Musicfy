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
import java.util.List;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class Song implements Serializable,Comparable<Song>{
     private  String titulo;
    private  int año;
    private  String duracion;
    private  List <String> interprete;

    public Song(String titulo, int año, String duracion, List <String> interprete) {
        this.titulo = titulo;
        this.año = año;
        this.duracion = duracion;
        this.interprete = interprete;
    }
    
    public Song() {
        this.titulo = null;
        this.interprete = null;
        this.año = 0;
        this.duracion = null;
    }
    public static Song crearSong(String titulo, int año, String duracion, List <String> interprete){
        return new Song(titulo,año,duracion,interprete);
    }
    public String Formato(){
        String listinterprete = String.join(", ", interprete);
        return String.format("|%s|%d|%s|%s|\n",titulo,año,duracion,listinterprete);
    }
    public String FormatoTitulo(){
        return String.format("%s",titulo);
    }

    public  List <String> FormatoSong(ArrayList <Song> canciones){
        List <String> texto = new ArrayList();
        for(Song cancion:canciones){         
            texto.add(cancion.FormatoTitulo());
        }
        return texto;
    }
    public  Song crearCancionAleatorio() throws Exception{
        Random r = new Random();
        String[][] cancion_aleatorio = null;
        String[][] artista_aleatorio = null;
        List <String> interprete_aleatorio = new ArrayList<String>();

        int[] año ={1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019};
        String[] duracion={"2 min 51 seg","2 min 2 seg","1 min 9 seg","3 min 20 seg","2 min 35 seg","3 min 40 seg"};

        cancion_aleatorio=importarAleatorio();
        int i1 = r.nextInt(cancion_aleatorio.length);
       
        artista_aleatorio=Artist.importarAleatorio();
         int i3 =1+r.nextInt(4);
         int i4;
              
       for(int i=0;i<i3;i++){
           i4=r.nextInt(artista_aleatorio.length);
           interprete_aleatorio.add(artista_aleatorio[i4][0]);
          }  
        
        Song cancion = Song.crearSong(cancion_aleatorio[i1][0],año[r.nextInt(año.length)],duracion[r.nextInt(duracion.length)],interprete_aleatorio);

        return cancion;
    } 
     public static String[][] importarAleatorio() throws Exception{
         String[][] cancion_aleatorio = null;
         
        Path path_cancion=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"aleatorios", "titulosCanciones.txt");
        cancion_aleatorio=importFromDisk(path_cancion.toFile(),"\t");
        
        return cancion_aleatorio;
     }

    public String getTitulo() {
        return titulo;
    }

    public int getAño() {
        return año;
    }

    public String getDuracion() {
        return duracion;
    }

    public List<String> getInterprete() {
        return interprete;
    }

    @Override
    public int compareTo(Song o) {
        return this.titulo.compareToIgnoreCase(o.titulo);
    }
    
    @Override
    public String toString() {
        return this.titulo;
    }



}
