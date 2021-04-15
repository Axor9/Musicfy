/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.yesOrNo;
import static com.coti.tools.OpMat.importFromDisk;
import com.coti.tools.Rutas;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;



/**
 *
 * @author Usuario
 */
public class Model {
      
       Musicfy ms = new Musicfy();


       public void ImportarDatos() throws IOException, Exception{
          Path path=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"binarios", "musicfy.bin");
          
          if (!path.toFile().exists()) {
              
             String[][] artista_archivo;
             Path path_artistas=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"datos", "artistas.txt");
             artista_archivo=importFromDisk(path_artistas.toFile(),"#");
             List <Artist> artistaimportado = new ArrayList<Artist>();

             for(int i=0;i<artista_archivo.length;i++){
            
              List <String> album_artistas = new ArrayList();
              
              String[] split = artista_archivo[i][6].split(";");
              
              album_artistas.addAll(Arrays.asList(split));
              
              Artist artista_import=Artist.crearArtistas(artista_archivo[i][0], artista_archivo[i][1],artista_archivo[i][2], 
                                                         artista_archivo[i][3], artista_archivo[i][4], artista_archivo[i][5],album_artistas);
            
              
              artistaimportado.add(artista_import);
              //System.out.printf("%s", artista_import.Formato());
             }
             ms.setArtistas(artistaimportado);

             System.out.println("\n");
             
             String[][] album_archivo;
             Path path_albumes=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"datos","albumes.txt");
             album_archivo=importFromDisk(path_albumes.toFile(),"\t");
             List <Album> albumimportado = new ArrayList<Album>();
             ArrayList <Song> cancioncoleccion=new ArrayList <Song>();


        for(int i=0;i<album_archivo.length;i++){
            
             Random r = new Random();             
              Song cancion_album = new Song();


              
              List <String> interprete_archivo = new ArrayList<String>();
              String[] split_interprete = album_archivo[i][1].split(";");
              String interprete=split_interprete[0];
               for(int j=0;j<split_interprete.length;j++){
                   try{
                     interprete=String.join(",",interprete,split_interprete[j+1]);
                   }catch(Exception ex){     
                   }
               }
              interprete_archivo.add(interprete);
              ArrayList <Song> cancionarchivo=new ArrayList <Song>();

              
              if(album_archivo[i][5].equalsIgnoreCase("sencillo")){
                 cancion_album=Song.crearSong("",1,"",null);
                 Song cancion_import=Song.crearSong(album_archivo[i][0],Integer.parseInt(album_archivo[i][2]),album_archivo[i][3],interprete_archivo);
                 cancioncoleccion.add(cancion_import);
                }
                else{
                  String[] duracion = {"2 min 54 seg","1 min 5 seg"};
                  String[] split1 = album_archivo[i][6].split(";");
                  String titulos_canciones =split1[0];
                 
                    for(int j=0;j<split1.length;j++){
                        Song cancion_import=Song.crearSong(split1[j],Integer.parseInt(album_archivo[i][2]),duracion[r.nextInt(duracion.length)], interprete_archivo);
                        cancioncoleccion.add(cancion_import);
                        try{
                        titulos_canciones=String.join(",",titulos_canciones,split1[j+1]);
                        }catch(Exception ex){
                        }
                    }
                    cancion_album=Song.crearSong(titulos_canciones,Integer.parseInt(album_archivo[i][2]),duracion[r.nextInt(duracion.length)],interprete_archivo);
                    cancionarchivo.add(cancion_album);
                    
              }
                
              Album album_import =Album.crearAlbumes(album_archivo[i][0],interprete_archivo,Integer.parseInt(album_archivo[i][2]), 
                                                   album_archivo[i][3],Integer.parseInt(album_archivo[i][4]),album_archivo[i][5],cancionarchivo);
              albumimportado.add(album_import);
              

          } 
              ms.setCanciones(cancioncoleccion);
              ms.setAlbumes(albumimportado);
      
        }
          else{
              System.out.println("Importando archivo Musicfy.bin....");
              Thread.sleep(1000);
               Musicfy msu=new Musicfy();
               List <Album> album = new ArrayList<>();
               List <Artist> artista = new ArrayList<>();
               List <Song> cancion = new ArrayList<>();
               List <Playlist> playlist = new ArrayList<>();

               FileInputStream fis;
                BufferedInputStream bis;
                ObjectInputStream ois= null;
                try{
                    fis= new FileInputStream(path.toFile());
                    bis= new BufferedInputStream(fis);
                    ois= new ObjectInputStream(bis);
                    ms=(Musicfy) ois.readObject();
                    ois.close();
            } catch(IOException | ClassNotFoundException ex){
                    out.println("No fue posible leer el archivo");
                    out.println(ex.toString());
                    exit(0);
            } finally{
                if(ois!=null){
                    try{
                        ois.close();
                    } catch(IOException ex){
                        err.println("ERROR");
                    }
                }
            }
              
              
            
          }
       }

    public void GeneracionAleatoria() throws Exception {
        ms.GeneracionAleatoria();
    }
    
    public List<Song> getCanciones() {
        return ms.getCanciones();
    }

    public List<Album> getAlbumes() {
        return ms.getAlbumes();
    }

    public List<Playlist> getPlaylists() {
        return ms.getPlaylists();
    }

    public List<Artist> getArtistas() {
        return ms.getArtistas();
    }
    
    public void Guardar() throws FileNotFoundException {
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos= null;
        DataOutputStream salida=null;
        
        Path path=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"binarios", "musicfy.bin");
         if (!path.toFile().exists()) {
            fos = new FileOutputStream(path.toFile());
            salida = new DataOutputStream(fos);
         }
        try{
            fos= new FileOutputStream(path.toFile());
            bos= new BufferedOutputStream(fos);
            oos= new ObjectOutputStream(bos);
            oos.writeObject(ms);
            oos.close();
        } catch(IOException ex){
            out.println("No fue posible guardar el archivo");
            out.println(ex.toString());
        } finally{
            if(oos!=null){
                try{
                    oos.close();
                } catch(IOException ex){
                    err.println("ERROR");
                }
            }
        }
    }

    public void exportarArtistas() throws IOException {
        Path ruta_directorio=Rutas.pathToFileInFolderOnDesktop("musicfy", "salida");
        File directorio = new File(ruta_directorio.toString());
            if(!directorio.exists()){
              directorio.mkdir();
            }  
        Path path_artistas=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"salida", "artista.col");
        File f_artistas = new File(path_artistas.toString());
        BufferedWriter bw ;
        List <Artist> lista_artista;
        lista_artista=ms.getArtistas();
           
        try {
            bw = new BufferedWriter(new FileWriter(f_artistas));
            for(Artist artista : lista_artista){
                    bw.write(artista.FormatoColumnas());
                } 
             bw.close();
        } catch(Exception ex) {
              System.err.println("Error exportando artistas");
        }
        
        
        
    }
     public void exportarAlbumes() {
        Path ruta_directorio=Rutas.pathToFileInFolderOnDesktop("musicfy", "salida");
        File directorio = new File(ruta_directorio.toString());
          if(!directorio.exists()){
                directorio.mkdir();
           }  
        BufferedWriter bw ;
        Path path_albumes=Rutas.pathToFileInFolderOnDesktop("musicfy"+File.separator+"salida", "albumes.html");
        File f_albumes = new File(path_albumes.toString());
        List <Album> lista_albumes;
        lista_albumes=ms.getAlbumes();
 
        try {
            bw = new BufferedWriter(new FileWriter(f_albumes));
            bw.write("<!DOCTYPE html><HTML><HEAD><meta charsets \"UTF-8\"><H1>Listado de albumes MUSICFY</H1></HEAD><BODY>");
            bw.write("<TABLE BORDER 1>");
            //bw.write("<TR><TH>Titulo</TH><TH>Interpretes</TH><TH>Año</TH><TH>Duracion</TH><TH>NumCanciones</TH><TH>Tipo</TH><TH>Canciones</TH></TR>");
           bw.write("<TR><TH>Titulo</TH><TH>Interpretes</TH><TH>Año</TH><TH>Duracion</TH><TH>NumCanciones</TH><TH>Tipo</TH></TR>");
            for(Album album : lista_albumes){
                    bw.write(album.FormatoHtml());
                } 
            bw.write("</TABLE BORDER 1></BODY></HTML>");
             bw.close();
        } catch(Exception ex) {
              System.err.println("Error exportando albumes");
        }
     }

    public void AltaAlbum() throws InterruptedException {
        String titulo;
        List <Album> listalbumes= new ArrayList <>();
        List <String> interprete = new ArrayList <>();
        String uninterprete;
        int año;
        int min,seg;
        String duracionmin,duracionseg,duracion,duracioncancion;
        int numero_canciones = 1;
        String tipo;
        String titulocancion;
        List <Song> canciones = ms.getCanciones();
        ArrayList <Song> canciones_nuevas = new ArrayList<>();
        Boolean salir=false;
        List <Artist> artistas=ms.getArtistas();
        List <Artist> lista_artistas=new ArrayList<>();
        List <Artist>lista_antigua=new ArrayList<>();
        
            titulo=Esdia.readString("Introduzca el titulo del album:");
            System.out.printf("Introduzca los interpretes del album:\n");
                do{
                   uninterprete=Esdia.readString("Nuevo interprete:");
                   interprete.add(uninterprete);
                   salir=yesOrNo("¿Dejar de añadir interpretes?");
                }while(!salir);
            año=Esdia.readInt("Introduzca el año de salida del album:");

            min=Esdia.readInt("Introduzca los minutos que dura el album del album:");
            seg=Esdia.readInt("Introduzca los segundos que dura el album del album:");

            duracionmin=String.join(" ",Integer.toString(min),"min");
            duracionseg=String.join(" ", Integer.toString(seg),"seg");
            duracion=String.join(" ",duracionmin,duracionseg);

                do{
                    tipo=Esdia.readString("Introduzca si es un sencillo o un album:");
                }while(!tipo.equalsIgnoreCase("sencillo") && !tipo.equalsIgnoreCase("album"));

            salir=false;
                if(tipo.equalsIgnoreCase("album")){
                    do{
                       titulocancion=Esdia.readString("Nueva cancion:");
                       min=Esdia.readInt("Introduzca los minutos que dura la cancion del album:");
                       seg=Esdia.readInt("Introduzca los segundos que dura la cancion del album:");

                       duracionmin=String.join(" ",Integer.toString(min),"min");
                       duracionseg=String.join(" ", Integer.toString(seg),"seg");
                       duracioncancion=String.join(" ",duracionmin,duracionseg);
                       Song cancion=Song.crearSong(titulocancion, año, duracioncancion, interprete);
                       numero_canciones++;
                       canciones_nuevas.add(cancion);
                       salir=yesOrNo("¿Dejar de añadir canciones?");

                    }while(!salir);
                }
                else{
                    Song cancion=Song.crearSong(titulo, año, duracion, interprete);
                    canciones_nuevas.add(cancion);
                }
              
              for(Artist artista:artistas){
                  for(String interprete_uno:interprete){
                      if(artista.getNombre().equalsIgnoreCase(interprete_uno)){
                          lista_antigua.add(artista);
                          System.out.printf("Añadiendo el album al artista %s\n",artista.getNombre());
                          Thread.sleep(1000);
                          artista.getAlbumes().add(titulo);
                          lista_artistas.add(artista);
                      }
                  }
              }
            Album album=Album.crearAlbumes(titulo, interprete, año, duracion, numero_canciones, tipo, canciones_nuevas);

            listalbumes.addAll(ms.getAlbumes());
            listalbumes.add(album);
            ms.setAlbumes(listalbumes);

            canciones.addAll(canciones_nuevas);
            ms.setCanciones(canciones);
            artistas.removeAll(lista_antigua);
            artistas.addAll(lista_artistas);
            ms.setArtistas(artistas);
        
    }


    public void BajaAlbum() throws InterruptedException {
        String titulo;
        int encontrado=0;
        List <Album> albumes=new ArrayList<>();
        albumes=ms.getAlbumes();
        List <Song> canciones = new ArrayList<>();
        canciones=ms.getCanciones();
        List <Artist> artistas = new ArrayList<>();
        artistas=ms.getArtistas();
        List <Song> canciones_nueva=new ArrayList<>();
        List <Artist> artistas_nueva=new ArrayList<>();
        List <Artist> artistas_otra=artistas;
        Artist aux = new Artist();
        
          titulo=Esdia.readString("\nIntroduzca el titulo del album que desea eliminar: ");
          
 
          for(Album album : albumes){
              if(titulo.equalsIgnoreCase(album.getTitulo())){
                  for(Artist artista : artistas){
                      //aux=artista;
                      for(String album_artista : artista.getAlbumes()){
                        if(titulo.equalsIgnoreCase(album_artista)){
                            //aux.getAlbumes().remove(titulo);
                            System.out.printf("Eliminando el album de %s....\n",artista.getNombre());
                            Thread.sleep(3000);
                            artistas_nueva.add(artista);
                        }
                     }
                  }
                  for(Song cancion : canciones){
                      if(album.getTipo().equalsIgnoreCase("album")){
                      for(Song cancion_album : album.getCanciones()){
                           String[] split=cancion_album.getTitulo().split(",");                            
                            for(int i=0;i<split.length;i++){
                                if(cancion.getTitulo().equalsIgnoreCase(split[i])){
                                    canciones_nueva.add(cancion);
                                }
                            }
                       }
                      }
                      else{
                         if(cancion.getTitulo().equalsIgnoreCase(album.getTitulo())){
                             canciones_nueva.add(cancion);
                          } 
                      }
                   }
                  albumes.remove(album);
                  encontrado=1;
                  break;
            }
          }
          
          if(encontrado==0){
            System.out.println("\nAlbum no encontrado");      
          }
          else{
            ms.setAlbumes(albumes);
            List <Artist> artista_otro=artistas_nueva;
            List <String> albumes_otro= new ArrayList<>();

            artistas.removeAll(artistas_nueva); 
            Iterator <Artist> it = artistas_nueva.listIterator();
             
            while(it.hasNext()){
                Artist artista=it.next();
                Iterator <String> it_album = artista.getAlbumes().listIterator();
               while(it_album.hasNext()){
                   String album=it_album.next();
                  if(titulo.equalsIgnoreCase(album)){
                      it_album.remove();
                  }
               }
            }
            artistas.addAll(artistas_nueva);
            ms.setArtistas(artistas);
            
            System.out.println("Eliminando las canciones....");
            Thread.sleep(3000);
            canciones.removeAll(canciones_nueva);
            ms.setCanciones(canciones);
            System.out.println("Album eliminado correctamente");
          }
          
        

    }

    public void ModificarAlbum() {
        String nombre,option;
        boolean encontrado=false;
        int año,numero_canciones,min,seg;
        String duracion,tipo,duracionmin,duracionseg;
        List <Album> album_m=ms.getAlbumes();
        
        nombre=Esdia.readString("Introduzca el titulo del album que desea modificar\n");
        Album album_editable=new Album();
        Album aux=new Album();
        
        for(Album album : ms.getAlbumes()){
            if(nombre.equalsIgnoreCase(album.getTitulo())){
                album_editable=album;
                aux=album;
                encontrado=true;
                break;
            }
        }
        if(encontrado==true){
            System.out.println("Album encontrado:");
            //No sabia si podia modificar el tipo y numero de canciones , no pone nada pero al no poder modificar la canciones no se que hacer
            /*option=Esdia.readString("1.-Modificar año"
                                   +"\n2.-Modificar duracion"
                                   +"\n3.-Modificar tipo"
                                   +"\n4.-Modificar numero de canciones   ");*/
            option=Esdia.readString("1.-Modificar año"
                                   +"\n2.-Modificar duracion"
                                   +"Opción: ");
            
            
            switch(option){
                case "1":
                    System.out.printf("El año de lanzamiento del album %s es: %d\n",album_editable.getTitulo(),album_editable.getAño());
                    año=Esdia.readInt("Introduzca el nuevo año:  ");
                    album_editable.setAño(año);
                    album_m.add(album_editable);
                    album_m.remove(aux);
                    ms.setAlbumes(album_m);
                    break;
                case "2":
                    System.out.printf("La duracion del album %s es: %s\n",album_editable.getTitulo(),album_editable.getDuracion());
                    min=Esdia.readInt("Introduzca los minutos que dura la cancion del album:");
                    seg=Esdia.readInt("Introduzca los segundos que dura la cancion del album:");
                    duracionmin=String.join(" ",Integer.toString(min),"min");
                    duracionseg=String.join(" ", Integer.toString(seg),"seg");
                    duracion=String.join(" ",duracionmin,duracionseg);
                    album_editable.setDuracion(duracion);
                    album_m.add(album_editable);
                    album_m.remove(aux);
                    ms.setAlbumes(album_m);
                    break;
                /*case "3":
                    System.out.printf("El tipo del album %s es: %s\n",album_editable.getTitulo(),album_editable.getTipo());
                    tipo=Esdia.readString("Introduzca el tipo del album:");
                     if(tipo.equalsIgnoreCase("sencillo")|| tipo.equalsIgnoreCase("album")){
                         album_editable.setTipo(tipo);
                        album_m.add(album_editable);
                        album_m.remove(aux);
                        ms.setAlbumes(album_m);
                     }
                     else{
                         System.out.println("El tipo que ha introducido es incorrecto");
                     }
                    break;
                case "4":
                    System.out.printf("El numero de canciones del album %s es: %d\n",album_editable.getTitulo(),album_editable.getNumero_canciones());
                    numero_canciones=Esdia.readInt("Introduzca el nuevo numero de canciones:  ");
                    album_editable.setNumero_canciones(numero_canciones);
                    album_m.add(album_editable);
                    album_m.remove(aux);
                    ms.setAlbumes(album_m);
                    break;*/
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
        else{
            System.out.println("El album que buscas no esta registrado\n");
        }

    }
    
    public void AltaArtista() {
      String nombre;
     String biografia;
     String instagram;
     String twitter;
     String facebook;
     String wikipedia;
     String nombre_album;
     List <String> albumes= new ArrayList<>();
     List <Artist> artistas= new ArrayList<>();
     artistas=ms.getArtistas();
     boolean salir=false;
     
        nombre=Esdia.readString("Introduzca el nombre del artista:\n");
        biografia=Esdia.readString("Introduzca la biografia del artista:\n");
        instagram=Esdia.readString("Introduzca el instagram del artista:\n");
        twitter=Esdia.readString("Introduzca el twitter del artista:\n");
        facebook=Esdia.readString("Introduzca el facebook del artista:\n");
        wikipedia=Esdia.readString("Introduzca la wikipedia del artista:\n");
        
        do{
            nombre_album=Esdia.readString("Introduzca un album del artista\n");
            albumes.add(nombre_album);
            salir=yesOrNo("¿Dejar de añadir albumes");
        }while(!salir);
        
        Artist artista = Artist.crearArtistas(nombre, biografia, instagram, twitter, facebook, wikipedia, albumes);
        artistas.add(artista);
        ms.setArtistas(artistas);
    }


    public void BajaArtista() {
        String nombre;
        boolean encontrado=false,encontrado_album=false;
        List <Album> albumes=new ArrayList<>();
        albumes=ms.getAlbumes();
        List <Song> canciones = new ArrayList<>();
        canciones=ms.getCanciones();
        List <Artist> artistas = new ArrayList<>();
        artistas=ms.getArtistas();
        List <Artist> artistas_borrar = new ArrayList<>();

        
          nombre=Esdia.readString("Introduzca el nombre del artista que desea eliminar: ");
          
          for(Artist artista : artistas){
              if(nombre.equalsIgnoreCase(artista.getNombre())){
                  for(Album album : albumes){
                      for(String interprete : album.getInterprete()){
                          String[] split1 = interprete.split(",");
                          for(int i=0;i<split1.length;i++){
                            if(nombre.equalsIgnoreCase(split1[i])){
                                encontrado_album=true; 
                            }
                          }
                     }
                  }
                 if(encontrado_album==false){
                  artistas_borrar.add(artista);
                 }
                 encontrado=true;
               }
          }        
          

          
          if(encontrado==false){
           System.out.println("Artista no encontrado");      
          }
           else{
            if(encontrado_album==false){
              artistas.removeAll(artistas_borrar);
              ms.setArtistas(artistas);
              System.out.println("Artista eliminado correctamente");
            }
            else{
              System.out.println("El artista tiene un album dado de alta , porfavor eliminelo antes ");  
           }
          }

         
          
        

    }

    public void ModificarArtista() {
        String nombre,option;    
        boolean encontrado=false;
        String biografia;
        String instagram;
        String twitter;
        String facebook;
        String wikipedia;
        List <Artist> artistas_m=ms.getArtistas();
        
        nombre=Esdia.readString("\nIntroduzca el nombre del artista que busca:\n");
        Artist artista_editable=new Artist();
        Artist aux=new Artist();
        
        for(Artist artista : ms.getArtistas()){
            if(nombre.equalsIgnoreCase(artista.getNombre())){
                artista_editable=artista;
                aux=artista;
                encontrado=true;
                break;
            }
        }
        if(encontrado==true){
            System.out.println("Artista encontrado:");
            option=Esdia.readString("1.-Modificar biografia"
                                   +"\n2.-Modificar instagram"
                                   +"\n3.-Modificar twitter"
                                   +"\n4.-Modificar facebook"
                                   +"\n5.-Modificar wikipedia"
                                   +"\nOpción: ");
            
            
            switch(option){
                case "1":
                    System.out.printf("La biografia del artista es %s es: \n%s\n",artista_editable.getNombre(),artista_editable.getBiografia());
                    biografia=Esdia.readString("Introduzca la nueva biografia:  ");
                    artista_editable.setBiografia(biografia);
                    artistas_m.add(artista_editable);
                    artistas_m.remove(aux);
                    ms.setArtistas(artistas_m);
                    break;
                case "2":
                    System.out.printf("El instagram del artista es %s es: \n%s\n",artista_editable.getNombre(),artista_editable.getInstagram());
                    instagram=Esdia.readString("Introduzca el nuevo instagram:  ");
                    artista_editable.setInstagram(instagram);
                    artistas_m.add(artista_editable);
                    artistas_m.remove(aux);
                    ms.setArtistas(artistas_m);
                    break;
                case "3":
                    System.out.printf("El twitter del artista es %s es: \n%s\n",artista_editable.getNombre(),artista_editable.getTwitter());
                    twitter=Esdia.readString("Introduzca el nuevo twitter:  ");
                    artista_editable.setTwitter(twitter);
                    artistas_m.add(artista_editable);
                    artistas_m.remove(aux);
                    ms.setArtistas(artistas_m);
                    break;
                case "4":
                    System.out.printf("El facebook del artista es %s es: \n%s\n",artista_editable.getNombre(),artista_editable.getFacebook());
                    facebook=Esdia.readString("Introduzca el nuevo facebook:  ");
                    artista_editable.setFacebook(facebook);
                    artistas_m.add(artista_editable);
                    artistas_m.remove(aux);
                    ms.setArtistas(artistas_m);
                    break;
                case "5":
                    System.out.printf("El wikipedia del artista es %s es: \n%s\n",artista_editable.getNombre(),artista_editable.getWikipedia());
                    wikipedia=Esdia.readString("Introduzca el nuevo wikipedia:  ");
                    artista_editable.setWikipedia(wikipedia);
                    artistas_m.add(artista_editable);
                    artistas_m.remove(aux);
                    ms.setArtistas(artistas_m);
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
        else{
            System.out.println("El artsita que buscas no esta registrado\n");
        }

    }

    public void CrearPlaylist() throws InterruptedException {
        Random r = new Random();
        int random,numero;
        String nombre;
        ArrayList <Song> canciones_playlist = new ArrayList<>();
        List <Playlist> plays=new ArrayList<>();
        nombre=Esdia.readString("Introduzca el nombre de la playlist\n");
        numero=Esdia.readInt("Introduzca el numero de canciones de la playlist:");
        
        for(int i=0;i<numero;i++){
            random=r.nextInt(ms.getCanciones().size());
            canciones_playlist.add(ms.getCanciones().get(random));
        }
        
        Playlist playlist=Playlist.crearPlaylist(nombre, canciones_playlist);
        plays.add(playlist);
        try{
        plays.addAll(ms.getPlaylists());
        }catch(Exception ex){  
        }
        System.out.println("Añadinedo playlist......");
        Thread.sleep(1000);
        ms.setPlaylists(plays);
    }

    public void EliminarCancionPlaylist() throws InterruptedException {
        String nombre,nombre_cancion;
        boolean encontrado=false;
        boolean salir=false;
        boolean encontrado_cancion=false;
        List <Song> canciones_borrado=new ArrayList<>();
        Playlist p = new Playlist();
        Playlist p_antiguo = new Playlist();
        
        nombre=Esdia.readString("Introduzca el nombre de la playlist:\n");
        try{
        for(Playlist play : ms.getPlaylists()){
            if(nombre.equalsIgnoreCase(play.getNombre())){ 
                p=play;
                p_antiguo=play;
                encontrado=true;
            }
        }

        
        if(encontrado==true){
            System.out.println("Playlist encontrada:");
            do{
                System.out.printf("Estas son las canciones de la playlist [%s]:\n",p.getNombre());
                for(Song cancion : p.getCanciones()){
                    System.out.printf("%s", cancion.Formato());
                }
                canciones_borrado.clear();
                nombre_cancion=Esdia.readString("Introduzca el nombre de la cancion:\n");
                    for(Song cancion : p.getCanciones()){
                        if(nombre_cancion.equalsIgnoreCase(cancion.getTitulo())){
                            encontrado_cancion=true;
                            System.out.println("Borrando cancion......");
                            Thread.sleep(1000);
                        }
                        else{
                            canciones_borrado.add(cancion);
                        }
                       
                    }
                    p.getCanciones().clear();
                    p.getCanciones().addAll(canciones_borrado);
                    if(p.getCanciones().isEmpty()){
                        break;
                    }
                    if(encontrado_cancion==true){
                    ms.getPlaylists().remove(p_antiguo);
                    ms.getPlaylists().add(p);
                    }
                    else{
                        System.out.println("Cancion no encontrada");
                    }

                    salir=yesOrNo("¿Desea seguir eliminando canciones?");
            }while(salir);
                
            
        }
        else{
            System.out.println("La Playlist que buscas no esta registrada\n");
        }
      }catch(Exception ex){
         System.out.println("La Playlist que buscas no esta registrada\n");
      }
        
    }

    public void AñadirCancionPlaylist() {
        String play,nombre;
        Playlist p = new Playlist();
        Playlist p_antiguo = new Playlist();
        boolean encontrado=false;
        boolean salir=false;
        List <String> interprete = new ArrayList <>();
        String uninterprete;
        int año;
        int min,seg;
        String duracionmin,duracionseg,duracion,duracioncancion;
        
            play=Esdia.readString("Introduzca el nombre de la playlist:\n");
            

        
        try{
            for(Playlist playlist : ms.getPlaylists()){
                if(play.equalsIgnoreCase(playlist.getNombre())){ 
                    p=playlist;
                    p_antiguo=playlist;
                    encontrado=true;
            }
           }
                if(encontrado==true){
                    System.out.println("Playlist encontrada:");
                    nombre=Esdia.readString("Introduzca el nombre de la cancion:\n");
                    
                    año=Esdia.readInt("Introduzca el año de la cancion:  ");

                    min=Esdia.readInt("Introduzca los minutos que dura la cancion:  ");
                    seg=Esdia.readInt("Introduzca los segundos que dura la cancion:  ");

                    duracionmin=String.join(" ",Integer.toString(min),"min");
                    duracionseg=String.join(" ", Integer.toString(seg),"seg");
                    duracion=String.join(" ",duracionmin,duracionseg);
                        do{
                           uninterprete=Esdia.readString("Nuevo interprete:\n");
                           interprete.add(uninterprete);
                           salir=yesOrNo("¿Seguir añadiendo interpretes?");
                        }while(!salir);
                        
                 Song cancion=Song.crearSong(nombre, año, duracion, interprete);
                 p.getCanciones().add(cancion);
                 ms.getPlaylists().remove(p_antiguo);
                 ms.getPlaylists().add(p);
                 ms.getCanciones().add(cancion);
                 
                }
                else{
                  System.out.println("La Playlist que buscas no esta registrada\n");  
                }
        
       }catch(Exception ex){
         System.out.println("La Playlist que buscas no esta registrada\n");
       }
    }



   
   
    
}
