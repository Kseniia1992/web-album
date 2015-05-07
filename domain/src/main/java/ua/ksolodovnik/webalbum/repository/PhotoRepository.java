package ua.ksolodovnik.webalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.ksolodovnik.webalbum.entity.Photo;

import java.util.List;

@Transactional
public interface PhotoRepository extends JpaRepository<Photo, Long>{

    Photo getPhotoByName(String name);

    @Query("select p from Photo p where p.additionDate = " +
            "(select max(photo.additionDate) from Photo photo where photo.name = p.name)")
    List<Photo> getLatestPhoto();
}
