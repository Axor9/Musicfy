/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import data.Album;
import data.Artist;
import data.Model;
import data.Playlist;
import data.Song;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author Usuario
 */
public class Controller {
    Model m = new Model();
    
    public void ImportarDatos() throws Exception{
        m.ImportarDatos();
    }

    public void GeneracionAleatoria() throws Exception {
        m.GeneracionAleatoria();
    }

    public void Guardar() throws FileNotFoundException {
        m.Guardar();
    }
    
    public void exportarArtistas() throws IOException {
        m.exportarArtistas();
    }
    public void exportarAlbumes() {
        m.exportarAlbumes();
    }
    
    public void AltaAlbum() throws InterruptedException {
        m.AltaAlbum();
    }
    public void BajaAlbum() throws InterruptedException{
        m.BajaAlbum();
    }
    public void AltaArtista() {
        m.AltaArtista();
    }
    public void BajaArtista(){
        m.BajaArtista();
    }

    public void ModificarAlbum() {
        m.ModificarAlbum();
    }

    public List<Album> getAlbum() {
        return m.getAlbumes();
    }

    public List<Artist> getArtista() {
        return m.getArtistas();
    }

    public List<Song> getCancion() {
        return m.getCanciones();
    }
    
    public List<Playlist> getPlaylist() {
        return m.getPlaylists();
    }

    public void ModificarArtista() {
        m.ModificarArtista();
    }

    public void CrearPlaylist() throws InterruptedException {
        m.CrearPlaylist();
    }

    public void EliminarCancionPlaylist() throws InterruptedException {
        m.EliminarCancionPlaylist();
    }

    public void AñadirCancionPlaylist() {
        m.AñadirCancionPlaylist();;
    }

    




}
