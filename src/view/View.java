/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.yesOrNo;
import controller.Controller;
import data.Album;
import data.Playlist;
import data.Song;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class View {
       Controller c = new Controller();


    public void runMenu(String menu) throws Exception {
       boolean salir=false;
       String option;
       
         do{
             System.out.println("----------------------------------------");
             System.out.println("|          Elige una opcion            |");
             System.out.println("----------------------------------------");
             option=readString(menu).toLowerCase();
             switch(option){
                 case "1":
                     GeneracionAleatoria();
                     break;
                 case "2":    
                      ExportarArchivos("1.-Exportar Artistas\n"
                                      +"2.-Exportar Albumes\n"
                                      +"q.-Salir\n"
                                      +"Opción: ");
                      break;
                 case "3":
                       EditarAlbum("1.-Añadir album\n"
                                  +"2.-Borrar album\n"
                                  +"3.-Modificar album\n"
                                  +"4.-Consultar album\n"
                                  +"q.-Salir\n"
                                  +"Opción: ");
                       break;
                 case "4":
                       EditarArtista("1.-Añadir artista\n"
                                  +"2.-Borrar artista\n"
                                  +"3.-Modificar artista\n"
                                  +"4.-Consultar artista\n"
                                  +"q.-Salir\n"
                                  +"Opción: ");
                       break;
                 case "5":
                        EditarPlaylist("1.-Añadir playlist\n"
                                      +"2.-Eliminar cancion\n"
                                      +"3.-Añadir cancion\n"
                                      +"q.-Salir\n"
                                      +"Opción:");
                        break;
                 case "6":
                        CancionesOrdenadas();
                        break;
                 case "q":
                        salir=yesOrNo("Desea realmente salir\n");
                        Guardar();
                        break;
                 default:
                     System.out.println("Opcion incorrecta\n");
             }
         }while(!salir);
    }
    
    public void ImportarDatos() throws Exception{
         c.ImportarDatos();
     }
     
    private void GeneracionAleatoria() throws Exception {
        c.GeneracionAleatoria();
    }

    private void ExportarArchivos(String menu) throws IOException {
              Boolean salir = false;
              String option;
     
           do{
             System.out.println("----------------------------------------");
             System.out.println("|          Elige una opcion            |");
             System.out.println("----------------------------------------");
             option=readString(menu).toLowerCase();
             switch(option){
                 case "1":
                     c.exportarArtistas();
                     break;
                 case "2":              
                     c.exportarAlbumes();
                     break;
                 case "q":
                    salir=yesOrNo("Desea realmente salir\n");
                    break;          
                 default:
                     System.out.println("Opcion incorrecta\n");
             }
           }while(!salir);
    }

    private void EditarAlbum(String menu) throws InterruptedException {      
              List <Album> albumes = new ArrayList <>();
              Boolean salir = false;
              String option;

              
           do{
             System.out.println("----------------------------------------");
             System.out.println("|          Elige una opcion            |");
             System.out.println("----------------------------------------");
             option=readString(menu).toLowerCase();
             switch(option){
                 case "1":
                     System.out.println("-----------------------------------");
                     System.out.println("|Opcion para añadir un nuevo album|");
                     System.out.println("-----------------------------------");
                     c.AltaAlbum();
                     break;
                 case "2":              
                     System.out.println("---------------------------------");
                     System.out.println("| Opcion para eliminar un album |");
                     System.out.println("---------------------------------");
                     c.BajaAlbum();
                     break;
                 case "3":
                     System.out.println("----------------------------------");
                     System.out.println("| Opcion para modificar un album |");
                     System.out.println("----------------------------------");
                     c.ModificarAlbum();
                     break;
                 case "4":
                     System.out.println("-------------------------------------");
                     System.out.println("|   Opcion para mostrar un album:   |");
                     System.out.println("-------------------------------------");
                     MostrarAlbumes();
                     break;
                 case "q":
                    salir=yesOrNo("Desea realmente salir\n");
                    break;          
                 default:
                     System.out.println("Opcion incorrecta\n");
             }
           }while(!salir);
    }

    private void EditarArtista(String menu) {
        Boolean salir = false;
        String option;
        
        do{
             System.out.println("----------------------------------------");
             System.out.println("|          Elige una opcion            |");
             System.out.println("----------------------------------------");
             option=readString(menu).toLowerCase();
             switch(option){
                 case "1":
                     System.out.println("-------------------------------------");
                     System.out.println("|Opcion para añadir un nuevo artista|");
                     System.out.println("-------------------------------------");
                     c.AltaArtista();
                     break;
                 case "2":              
                     System.out.println("-------------------------------- ");
                     System.out.println("|Opcion para eliminar un artista|");
                     System.out.println("---------------------------------");
                     c.BajaArtista();
                     break;
                 case "3":
                     System.out.println("-------------------------------- ");
                     System.out.println("|Opcion para modificar un artista|");
                     System.out.println("---------------------------------");
                     c.ModificarArtista();
                     break;
                 case "4":
                     System.out.println("------------------------------------------------");
                     System.out.println("|Opcion para mostrar los albumes de un artista:|");
                     System.out.println("------------------------------------------------");
                     MostrarArtistas();
                     break;
                 case "q":
                    salir=yesOrNo("Desea realmente salir\n");
                    break; 
                 default:
                     System.out.println("Opcion incorrecta\n");
             }
        }while(!salir);
    }

    private void EditarPlaylist(String menu) throws InterruptedException {
        Boolean salir=false;
        String option;
        
         do{
             System.out.println("----------------------------------------");
             System.out.println("|          Elige una opcion            |");
             System.out.println("----------------------------------------");
             option=readString(menu).toLowerCase();
             switch(option){
                 case "1":
                     System.out.println("--------------------------------------");
                     System.out.println("|Opcion para crear una nueva playlist|");
                     System.out.println("--------------------------------------");
                     c.CrearPlaylist();
                     break;
                 case "2":   
                     System.out.println("------------------------------------------------");
                     System.out.println("|Opcion para elimnar una cancion de la playlist|");
                     System.out.println("------------------------------------------------");
                     try{
                     System.out.println("Estas son las playlists disponibles:");
                     MostrarPlaylist();
                     }catch(Exception ex){
                         System.out.println("No existen playlist por favor añada una");
                         break;
                     }
                     c.EliminarCancionPlaylist();
                     break;
                 case "3":
                     System.out.println("-----------------------------------------------");
                     System.out.println("|Opcion para añadir una cancion de la playlist|");
                     System.out.println("-----------------------------------------------");
                     try{
                     System.out.println("Estas son las playlists disponibles:");
                     MostrarPlaylist();
                     }catch(Exception ex){
                         System.out.println("No existen playlist por favor añada una");
                         break;
                     }
                     c.AñadirCancionPlaylist();
                     break;
                 case "q":
                    salir=yesOrNo("Desea realmente salir\n");
                    break; 
                 default:
                     System.out.println("Opcion incorrecta\n");
             }
         }while(!salir);
    }

    private void EditarCanciones() {
      
    }

    private void Guardar() throws FileNotFoundException {
        c.Guardar();
    }

    private void MostrarAlbumes() {
       List <Album> albumes = c.getAlbum();
       String nombre;
       boolean encontrado=false;
       
        nombre=Esdia.readString("Introduzca el titulo de un album a mostrar\n");
       
        for(Album album : albumes){
            if(nombre.equalsIgnoreCase(album.getTitulo())){
              System.out.printf("%s", album.Formato());
              encontrado=true;
            }
        }
        if(encontrado==false){
            System.out.println("El album que busca no esta registrado");
        }
    }
    private void MostrarArtistas(){
        List <Album> albumes_ordenados = new ArrayList<>();
        List <Album> albumes = c.getAlbum();
        String nombre;
         boolean encontrado=false;
               
        nombre=Esdia.readString("Introduzca el nombre del artista\n");
        for(Album album : albumes){
            for(String s:album.getInterprete()){
                String[] split =s.split(",");
                for(int i=0;i<split.length;i++){
                    if(nombre.equalsIgnoreCase(split[i])){
                        albumes_ordenados.add(album);
                        encontrado=true;
                   }
                }
            }
        }
        if(encontrado==false){
            System.out.println("El artista que busca no esta registrado");
        }
        else{  
             Collections.sort(albumes_ordenados);
            int i=0;
            for(Album album : albumes_ordenados){
                System.out.printf("%s", album.FormatoArtista());
            }
        }
    }
    private void MostrarPlaylist(){
        List <Playlist> playlist = c.getPlaylist();
        
        
        for(Playlist play : playlist){
            System.out.printf("%s", play.Formato());
        }
    }
    public void CancionesOrdenadas(){
        List <Song> canciones = c.getCancion();

        Comparator<Song> c1=Comparator.comparing(Song::getAño);
        Comparator<Song> c2=Comparator.comparing(Song::getTitulo);
        canciones.sort(c1.thenComparing(c2)); 
        System.out.println("Estas son la canciones disponibles");
        
          for(Song cancion : canciones){
            System.out.printf("%s",cancion.Formato());
            }
        
    }
      
}
