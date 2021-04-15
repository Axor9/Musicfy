

all: 
	javac -cp /home/angel/Escritorio/70915467G/biblioteca.jar aplicacion/Aplicacion.java view/View.java controller/Controller.java data/Model.java data/Musicfy.java data/Album.java data/Artist.java data/Song.java data/Playlist.java
	jar cvmf manifest.mf musicfy.jar /home/angel/Escritorio/70915467G/biblioteca.jar aplicacion/Aplicacion.class view/View.class controller/Controller.class data/Musicfy.class data/Model.class data/Album.class data/Artist.class data/Song.class data/Playlist.class
lean:
	rm -rf *.class
clean:
	rm -rf *.class
	rm -rf musicfy.jar
