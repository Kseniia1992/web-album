package ua.ksolodovnik.webalbum.service;


import ua.ksolodovnik.webalbum.entity.Album;
import ua.ksolodovnik.webalbum.entity.Catalog;
import ua.ksolodovnik.webalbum.service.exception.CatalogException;

import java.util.List;

public interface CatalogService {

    void saveCatalog(Catalog catalog);

    List<Catalog> getAllPageble();

    Catalog getCatalogById(long id);

    void deleteCatalogById(long id);

    Catalog getCatalogByName(String name);

    void addAlbumToCatalog(Catalog catalog, Album album) throws CatalogException;

}
