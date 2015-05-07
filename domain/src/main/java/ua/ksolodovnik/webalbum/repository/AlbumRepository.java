package ua.ksolodovnik.webalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.ksolodovnik.webalbum.entity.Album;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long>{

    Album getAlbumByName(String name);

    @Query("select a from Album a where a.creationDate = " +
            "(select max(al.creationDate) from Album al where al.name = a.name)")
    List<Album> getLatestAlbum();
}
