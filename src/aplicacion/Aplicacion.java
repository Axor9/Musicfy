/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import view.View;

/**
 *
 * @author Ángel Martín Ramos 70915467G
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
          View v = new View();
        
        v.ImportarDatos();
        
        v.runMenu("\n1.-Generacion aleatoria"
                 +"\n2.-Exportar Archivos"
                 +"\n3.-Editar Album"
                 +"\n4.-Editar Artista"
                 +"\n5.-Editar Playlist"
                 +"\n6.-Ver Canciones"
                 +"\nq.-Salir"
                 +"\nOpción: ");
        
        
    }
  }
    

