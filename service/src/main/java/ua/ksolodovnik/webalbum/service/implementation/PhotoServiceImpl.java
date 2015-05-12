package ua.ksolodovnik.webalbum.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ksolodovnik.webalbum.entity.Photo;
import ua.ksolodovnik.webalbum.repository.PhotoRepository;
import ua.ksolodovnik.webalbum.service.PhotoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Photo Service class.
 * All methods will use @Transactional because
 * they will get @Lob - the Large object
 * @author ksolodovnik
 */
@Service("photoService")
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    /**
     * Save photo to db
     * @param photo
     */
    @Override
    @Transactional
    public void savePhoto(Photo photo){
        photoRepository.save(photo);
    }

    /**
     * Get all photos
     * @return list of photos
     */
    @Override
    @Transactional
    public List<Photo> getAllPageble(){
        Page photoPage = photoRepository.findAll(new PageRequest(0,20));
        return photoPage.getContent();
    }

    /**
     * Get photo by id
     * @param id
     * @return photo
     */
    @Override
    @Transactional
    public Photo getPhotoById(long id){
        return photoRepository.findOne(id);
    }

    /**
     * Delete photo by id
     * @param id
     */
    @Override
    @Transactional
    public void deletePhotoById(long id){
        photoRepository.delete(id);
    }

    /**
     * Get photo by name
     * @param name
     * @return photo
     */
    @Override
    @Transactional
    public Photo getPhotoByName(String name){
        return photoRepository.getPhotoByName(name);
    }

    /**
     * Get photo with the latest date of addition.
     * This method returns the list of photos because
     * there can be several photos which were added at the same time
     * @return list
     */
    @Override
    @Transactional
    public List<Photo> getLatestPhoto(){
        return photoRepository.getLatestPhoto();
    }

    @Override
    @Transactional
    public List<byte[]> getAllImages() {
        List<Photo> photoList = photoRepository.findAll();
        List<byte[]> byteList = new ArrayList<>();
        for(Photo photo : photoList){
            byteList.add(photo.getImage());
        }
        return byteList;
    }


}
