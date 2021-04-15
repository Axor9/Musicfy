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
public class Album implements Serializable,Comparable<Album>{


    private  String titulo;
    private  List <String> interprete;
    private  int año;
    private  String duracion;
    private  int numero_canciones;
    private  String tipo;
    private  ArrayList <Song> canciones;
    

    public Album(String titulo, List <String> interprete, int año, String duracion, int numero_canciones, String tipo, ArrayList <Song> canciones) {
        this.titulo = titulo;
        this.interprete = interprete;
        this.año = año;
        this.duracion = duracion;
        this.numero_canciones = numero_canciones;
        this.tipo = tipo;
        this.canciones = canciones;
    }
 
   public Album() {
        this.titulo = null;
        this.interprete = null;
        this.año = 0;
        this.duracion = null;
        this.numero_canciones = 0;
        this.tipo = null;
        this.canciones = null;
    }
   
    static Album crearAlbumes(String titulo, List <String> interprete, int año, String duracion, int numero_canciones, String tipo, ArrayList <Song> canciones){
        return new Album(titulo,interprete,año,duracion,numero_canciones,tipo,canciones);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setInterprete(List<String> interprete) {
        this.interprete = interprete;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setNumero_canciones(int numero_canciones) {
        this.numero_canciones = numero_canciones;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    
    public String getTitulo() {
        return titulo;
    }

    public List<String> getInterprete() {
        return interprete;
    }

    public int getAño() {
        return año;
    }

    public String getDuracion() {
        return duracion;
    }

    public int getNumero_canciones() {
        return numero_canciones;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }
   
    
    public Album crearAlbumAleatorio() throws Exception{
        Random r = new Random();
        String[][] album_aleatorio = null;
        String[][] cancion_aleatorio = null;
        String[][] artista_aleatorio = null;
        
        List <String> interprete_aleatorio = new ArrayList<String>();
        ArrayList <Song> canciones_aleatorias = new ArrayList<Song>();
        

        
        String[] tipo = {"album","sencillo"};
        int[] año ={1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019};
        String[] duracion={"2 min 51 seg","2 min 2 seg","1 min 9 seg","3 min 20 seg","20 min 3 seg","10 min 40 seg"};
        String[] duracion_cancion={"2 min 51 seg","2 min 2 seg","1 min 9 seg","3 min 20 seg"};
        String duracionfinal=duracion[ r.nextInt(duracion.length)];
        album_aleatorio=importarAleatorio();
        Album album = new Album(); 
        int i1 = r.nextInt(album_aleatorio.length);
        int i2=r.nextInt(tipo.length);
        int ncanciones;
        int año_al=año[ r.nextInt(año.length)];
        Song cancion_nueva;

        artista_aleatorio= Artist.importarAleatorio();
        int i3 =1+r.nextInt(4);
        int i4=0;
              
       for(int i=0;i<i3;i++){
           i4=r.nextInt(artista_aleatorio.length);
           interprete_aleatorio.add(artista_aleatorio[i4][0]);
          }  
        
        cancion_aleatorio = Song.importarAleatorio();
        Song cancion_aleatoria;
        if(tipo[i2].equalsIgnoreCase("sencillo")){
          cancion_nueva=Song.crearSong(album_aleatorio[i1][0], año_al, duracionfinal, interprete_aleatorio);
          canciones_aleatorias.add(cancion_nueva);
          ncanciones=1;
        }
        else{
          int i5 = 1+r.nextInt(5);
          ncanciones=i5;
          int i6;
         
          for(int i=0;i<i5;i++){
              i6=r.nextInt(cancion_aleatorio.length);
              cancion_nueva=Song.crearSong(cancion_aleatorio[i6][0],año_al, duracion_cancion[r.nextInt(duracion_cancion.length)], interprete_aleatorio);
              canciones_aleatorias.add(cancion_nueva);
          }     
        }   
       
        
        album=Album.crearAlbumes(album_aleatorio[i1][0],interprete_aleatorio,año_al,duracionfinal,
                                 ncanciones,tipo[i2],canciones_aleatorias);
                
        return album;

 
    }   

    public String Formato(){
        Song c = new Song();
        String listcancion = String.join(", ", c.FormatoSong(canciones));
        String listinterprete =String.join(", ", interprete);
        
         if(tipo.equalsIgnoreCase("sencillo")){
           return String.format("|%s|%s|%d|%s|%d|%s|\n",titulo,listinterprete,año,duracion,numero_canciones,tipo);
         }
         else{
         return String.format("|%s|%s|%d|%s|%d|%s|%s|\n",titulo,listinterprete,año,duracion,numero_canciones,tipo,listcancion);
         }
         
    }
    public String FormatoArtista(){
        String listinterprete =String.join(", ", interprete);
        return String.format("|%-35s|%-35s|%-35d|%-35s|%-35d|%-35s|\n",titulo,listinterprete,año,duracion,numero_canciones,tipo);

    }
    public static String[][] importarAleatorio() throws Exception{
        String[][] album_aleatorio = null;
        
        Path path_albumes=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"aleatorios", "nombresAlbumes.txt");
        album_aleatorio=importFromDisk(path_albumes.toFile(),"\t"); 
        
        return album_aleatorio;
    }

    public String  FormatoHtml() {
        Song cancion = new Song();
        String listcancion = String.join(", ",cancion.FormatoSong(canciones));
        String listinterprete =String.join(", ", interprete);
        /*return String.format("<TR>"
                           +"<TD>%s</TD>"
                           + "<TD>%s</TD>"
                           + "<TD>%d</TD>"
                           + "<TD>%s</TD>"
                           + "<TD>%d</TD>"
                           + "<TD>%s</TD>"
                           + "<TD>%s</TD></TR>",titulo,listinterprete,año,duracion,numero_canciones,tipo,listcancion);*/
        return String.format("<TR>"
                           +"<TD>%s</TD>"
                           + "<TD>%s</TD>"
                           + "<TD>%d</TD>"
                           + "<TD>%s</TD>"
                           + "<TD>%d</TD>"
                           + "<TD>%s</TD>"
                           + "</TR>",titulo,listinterprete,año,duracion,numero_canciones,tipo);
    }
    @Override
    public int compareTo(Album o) {
        if(this.año> o.getAño()){
        return 1;
        }
        else if(this.año == o.getAño()){
            return 0;
        }
        else{
           return -1;
        }
        
    }


 
}
