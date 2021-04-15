/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.coti.tools.Esdia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class Musicfy implements Serializable{

    private   List <Song> canciones;
    private  List <Album> albumes;
    private  List <Playlist> playlists;
    private  List <Artist> artistas;
    
    
    public Musicfy(List <Song> canciones, List <Album> albumes, List <Playlist> playlists, List <Artist> artistas) {
        this.canciones = canciones;
        this.albumes = albumes;
        this.playlists = playlists;
        this.artistas = artistas;
    }
    
    public Musicfy() {
        this.canciones = null;
        this.albumes = null;
        this.playlists = null;
        this.artistas = null;
    }
    
    static Musicfy crearMusicfy(List <Song> canciones, List <Album> albumes, List <Playlist> playlists, List <Artist> artistas){
        return new Musicfy(canciones,albumes,playlists,artistas);
    }

    public  void GeneracionAleatoria() throws Exception {
        Random r = new Random();
        
        List <Artist> artista_aleatorio = new ArrayList <Artist>();
        List <Album> album_aleatorio = new ArrayList <Album>();
        List <Playlist> playlist_aleatorio = new ArrayList <Playlist>();
        List <Song> cancion_aleatorio = new ArrayList <Song>();
        
        Artist artista=new Artist();
        Album album=new Album();
        Song cancion=new Song();
        Playlist play=new Playlist();
        
        int i1=Esdia.readInt("Introduzca el numero de artistas que desea crear:  ");
        int i2=Esdia.readInt("Introduzca el numero de albumes que desea crear:  ");;
        int i3=Esdia.readInt("Introduzca el numero de canciones que desea crear:  ");;
        int i4=Esdia.readInt("Introduzca el numero de playlist que desea crear:  ");;

        for(int i=0;i<i1;i++){
            artista = artista.crearArtistaAleatorio();
            //System.out.printf("%s",artista.Formato());
            artista_aleatorio.add(artista);
        }
        setArtistas(artista_aleatorio);
        
        for(int i=0;i<i2;i++){
            album = album.crearAlbumAleatorio();
            //System.out.printf("%s",album.Formato());
            album_aleatorio.add(album);
            cancion_aleatorio.addAll(album.getCanciones());
        }
        setAlbumes(album_aleatorio);
        
        for(int i=0;i<i3;i++){
            cancion = cancion.crearCancionAleatorio();
            //System.out.printf("%s",cancion.Formato());
            cancion_aleatorio.add(cancion);
        }
        
        setCanciones(cancion_aleatorio);
        
        
        for(int i=0;i<i4;i++){
            play = play.crearPlaylistAleatorio();
            //System.out.printf("%s",play.Formato());
            playlist_aleatorio.add(play);
        }
        setPlaylists(playlist_aleatorio);
        

    }

    public List<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Artist> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artist> artistas) {
        this.artistas = artistas;
    }
    public Musicfy getMusicfyFinal(){
        Musicfy m = new Musicfy();
        this.albumes=m.getAlbumes();
        this.artistas=m.getArtistas();
        this.canciones=m.getCanciones();
        this.playlists=m.getPlaylists();
        
        return m;
    }


}
