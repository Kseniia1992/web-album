package ua.ksolodovnik.webalbum.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ksolodovnik.webalbum.entity.Album;
import ua.ksolodovnik.webalbum.repository.AlbumRepository;
import ua.ksolodovnik.webalbum.service.AlbumService;

import java.util.List;

/**
 * Album Service class
 * @author ksolodovnik
 */
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    /**
     * Save album to db
     * @param album
     */
    @Override
    @Transactional
    public void saveAlbum(Album album){
        albumRepository.save(album);
    }

    /**
     * Get all albums
     * @return list of albums
     */
    @Override
    public List<Album> getAll(){
        Page albumPage = albumRepository.findAll(new PageRequest(0,20));
        return albumRepository.findAll();
    }

    /**
     * Get album by id
     * @param id
     * @return album
     */
    @Override
    public Album getAlbumById(long id){
        return albumRepository.findOne(id);
    }

    /**
     * Delete album by id
     * @param id
     */
    @Override
    @Transactional
    public void deleteAlbumById(long id){
        albumRepository.delete(id);
    }

    /**
     * Get album by name
     * @param name
     * @return album
     */
    @Override
    public Album getAlbumByName(String name){
        return albumRepository.getAlbumByName(name);
    }

    /**
     * Get album with the latest date of creation.
     * This method returns the list of albums because
     * there can be several albums which were created at the same time
     * @return album
     */
    @Override
    public List<Album> getLatestAlbum(){
        return albumRepository.getLatestAlbum();
    }

}
