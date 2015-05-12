package ua.ksolodovnik.webalbum.service;


import ua.ksolodovnik.webalbum.entity.Photo;

import java.util.List;

public interface PhotoService {

    void savePhoto(Photo photo);

    List<Photo> getAllPageble();

    Photo getPhotoById(long id);

    void deletePhotoById(long id);

    Photo getPhotoByName(String name);

    List<Photo> getLatestPhoto();

    List<byte[]> getAllImages();

}
