package ua.ksolodovnik.webalbum.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ksolodovnik.webalbum.entity.Album;
import ua.ksolodovnik.webalbum.entity.Catalog;
import ua.ksolodovnik.webalbum.repository.CatalogRepository;
import ua.ksolodovnik.webalbum.service.CatalogService;
import ua.ksolodovnik.webalbum.service.exception.CatalogException;

import java.util.ArrayList;
import java.util.List;

/**
 * Catalog Service class
 * @author ksolodovnik
 */
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService{

    @Autowired
    private CatalogRepository catalogRepository;

    /**
     * Save catalog to db
     * @param catalog
     */
    @Override
    @Transactional
    public void saveCatalog(Catalog catalog){
        catalogRepository.save(catalog);
    }

    /**
     * Get all catalogs
     * @return list of catalogs
     */
    @Override
    public List<Catalog> getAllPageble(){
        Page catalogPage = catalogRepository.findAll(new PageRequest(0,20));
        return catalogPage.getContent();
    }

    /**
     * Get catalog by id
     * @param id
     * @return catalog
     */
    @Override
    public Catalog getCatalogById(long id){
        return catalogRepository.findOne(id);
    }

    /**
     * Delete catalog by id
     * @param id
     */
    @Override
    @Transactional
    public void deleteCatalogById(long id){
        catalogRepository.delete(id);
    }

     /**
     * Get catalog by name
     * @param name
     * @return catalog
     */
    @Override
    public Catalog getCatalogByName(String name){
        return catalogRepository.getCatalogByName(name);
    }


    /**
     * Add album to catalog where catalog category
     * matches album category
     * @param catalog
     * @param album
     * @throws CatalogException - catalog category
     * doesn't match album category
     */
    @Override
    public void addAlbumToCatalog(Catalog catalog, Album album)throws CatalogException{
        List<Album> albumList = new ArrayList<>();
        if (catalog.getCategory().equals(album.getCategory())) {
            albumList.add(album);
            catalog.setAlbums(albumList);
            album.setCatalog(catalog);
            saveCatalog(catalog);
        } else {
            throw new CatalogException("Category do not match");
        }
    }
}
