package ua.ksolodovnik.webalbum.service;

import ua.ksolodovnik.webalbum.entity.Album;

import java.util.List;

public interface AlbumService {

    void saveAlbum(Album album);

    List<Album> getAll();

    Album getAlbumById(long id);

    void deleteAlbumById(long id);

    Album getAlbumByName(String name);

    List<Album> getLatestAlbum();

}
