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
public class Artist implements Serializable{
     private  String nombre;
   private  String biografia;
   private  String instagram;
   private  String twitter;
   private  String facebook;
   private  String wikipedia;
   private  List <String> albumes;

    public Artist(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, List <String> albumes) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.wikipedia = wikipedia;
        this.albumes = albumes;
    }
    
    static Artist crearArtistas(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, List <String> albumes){
        return new Artist(nombre,biografia,instagram,twitter,facebook,wikipedia,albumes);
    }

    Artist() {
        this.nombre = "";
        this.biografia = "";
        this.instagram = "";
        this.twitter = "";
        this.facebook = "";
        this.wikipedia = "";
        this.albumes = null;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public void setAlbumes(List<String> albumes) {
        this.albumes = albumes;
    }

    
    public String getNombre() {
        return nombre;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public List<String> getAlbumes() {
        return albumes;
    }
    
    
    public  Artist crearArtistaAleatorio() throws Exception{
        Random r = new Random();
        String[][] artista_aleatorio = null;
        String[][] album_aleatorio = Album.importarAleatorio();
        List <String> album_aleatoria= new ArrayList <String>();
        String[] adjetivo_aleatorio={"cantante","guitarrista","bateria","trapero","rockero"};
        String[] años_aleatorios={"50","60","70","80","90","2000"};
        
        artista_aleatorio=importarAleatorio();
        int i1 = r.nextInt(artista_aleatorio.length);
        int i2 = r.nextInt(adjetivo_aleatorio.length);
        int i3 = r.nextInt(años_aleatorios.length);
        

       
        int i7 =1+r.nextInt(4);
        int i8=r.nextInt(artista_aleatorio.length); 
        
       for(int i=0;i<i7;i++){
           album_aleatoria.add(album_aleatorio[i8][0]);
           i8=r.nextInt(artista_aleatorio.length);
          }  
       String biografia=String.format("El artista %s es un %s que vivio en los %s",artista_aleatorio[i1][0],adjetivo_aleatorio[i2],años_aleatorios[i3]);
       String[] instagram={String.format("%sOFFICIAL",artista_aleatorio[i1][0]),
                           String.format("%s_music",artista_aleatorio[i1][0]),
                           String.format("The%s",artista_aleatorio[i1][0])};
       String[] twitter={String.format("@%sOFFICIAL",artista_aleatorio[i1][0]),
                           String.format("@%s_music",artista_aleatorio[i1][0]),
                           String.format("@The%s",artista_aleatorio[i1][0])};
       String[] facebook={String.format("%s_facebook",artista_aleatorio[i1][0]),
                           String.format("%s_oficial",artista_aleatorio[i1][0]),
                           String.format("Original%s",artista_aleatorio[i1][0])};
       String wikipedia = String.format("https://es.wikipedia.org/wiki/%s",artista_aleatorio[i1][0]);
       
       int i4 = r.nextInt(instagram.length);
        int i5 = r.nextInt(twitter.length);
        int i6 = r.nextInt(facebook.length);   
        
        Artist artista = Artist.crearArtistas(artista_aleatorio[i1][0],biografia, instagram[i4], 
                                              twitter[i5], facebook[i6],wikipedia, album_aleatoria);
       

        return artista;
    } 
    
    public String Formato(){
         String listalbumes = String.join(", ", albumes);
         return String.format("|%s|%s|%s|%s|%s|%s|%s|\n",nombre,biografia,instagram,twitter,facebook,wikipedia,listalbumes);
         
    }
    public String FormatoColumnas(){
        String listalbumes = String.join(", ", albumes);
         return String.format("|%-50s|%-70s|%-50s|%-50s|%-50s|%-70s|%-70s|\n",nombre,biografia,instagram,twitter,facebook,wikipedia,listalbumes);
         
    }

       
     public static String[][] importarAleatorio() throws Exception{
         String[][] artista_aleatorio = null;
         
        Path path_cancion=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"aleatorios", "nombresArtistas.txt");
        artista_aleatorio=importFromDisk(path_cancion.toFile(),"\t");
        
        
        return artista_aleatorio;
     }
}
