package ua.ksolodovnik.webalbum.testRepository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import ua.ksolodovnik.webalbum.entity.Album;
import ua.ksolodovnik.webalbum.entity.Catalog;
import ua.ksolodovnik.webalbum.entity.Photo;
import ua.ksolodovnik.webalbum.repository.AlbumRepository;
import ua.ksolodovnik.webalbum.repository.PhotoRepository;
import ua.ksolodovnik.webalbum.util.InitDate;
import ua.ksolodovnik.webalbum.util.SaveImage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "file:web/src/main/webapp/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class TestAlbumRepository {

    private static Photo photo;
    private static Album album;
    private static Album album2;
    private static Catalog catalog;
    private static List<Photo> photoList;
    private static List<Album> albumList;
    private static Page<Album> albumPage;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @BeforeClass
    public static void init(){
        photo = new Photo();
        album = new Album();
        album2 = new Album();
        catalog = new Catalog();
        photoList = new ArrayList<>();
        albumList = new ArrayList<>();

        photo.setName("photo1");
        photo.setImage(SaveImage.saveImg());

        catalog.setName("catalog");

        album.setName("animals");
        album.setCategory("nature");
        photoList.add(photo);
        album.setPhotos(photoList);
        album.setCatalog(catalog);

        Photo photo2 = new Photo();
        photo2.setName("some name");
        List<Photo> photos = new ArrayList<>();
        photos.add(photo2);

        Catalog catalog2 = new Catalog();
        catalog2.setName("catalog2");

        album2.setName("people");
        album2.setCategory("society");
        album2.setCatalog(catalog2);
        album2.setCreationDate(InitDate.setDate());
        album2.setPhotos(photos);

    }

    @Before
    public void save(){
        albumRepository.save(album);
        albumRepository.save(album2);
    }

    @Test
    public void testSavePhoto(){
        album = albumRepository.getAlbumByName("animals");
        assertNotNull(album);

        photoList = album.getPhotos();
        assertNotNull(photoList);

        catalog = album.getCatalog();
        assertNotNull(catalog);
    }

    @Test
    public void testGetAll(){
        albumPage = albumRepository.findAll(new PageRequest(0,2));
        albumList = albumPage.getContent();
        assertNotNull(albumList);

        album = albumList.get(albumList.size()-1);
        assertNotNull(album);
        assertNotNull(album.getId());
        assertNotNull((album.getCreationDate()));
        assertNotNull(album.getCatalog());
        assertNotNull(album.getPhotos());
        assertTrue(album.getName().equals("people"));
        assertTrue(album.getCatalog().getName().equals("catalog2"));
        assertTrue(album.getCategory().equals("society"));
    }

    @Test
    public void testGetAlbumById(){
        album = albumRepository.findOne(1L);
        assertNotNull(album);
        assertNotNull(album.getCreationDate());
        assertNotNull(album.getPhotos());
        assertNotNull(album.getCatalog());
        assertTrue(album.getName().equals("animals"));
    }

    @Test
    public void testGetAlbumByName(){
        album = albumRepository.getAlbumByName("animals");
        assertNotNull(album);
        assertNotNull(album.getId());
        assertNotNull(album.getCatalog());
        assertNotNull(album.getPhotos());
        assertNotNull(album.getCreationDate());
        assertTrue(album.getName().equals("animals"));
        assertTrue(album.getCatalog().getName().equals("catalog"));
    }

    @Test
    public void testGetLatestAlbum(){
        albumList = albumRepository.getLatestAlbum();
        assertNotNull(albumList);

        album2 = albumList.get(albumList.size()-1);
        album = albumRepository.getAlbumByName("animals");

        assertTrue(album2.getCreationDate().after(album.getCreationDate()));
    }

    @Test
    public void testDeleteAlbumBuId(){
        Photo photo3 = new Photo();
        photo3.setName("photo3");

        List<Photo> list = new ArrayList<>();
        list.add(photo3);

        Album album3 = new Album();
        album3.setName("art");
        album3.setPhotos(list);
        albumRepository.save(album3);
        photoRepository.save(photo3);

        photo3 = photoRepository.getPhotoByName("photo3");
        assertNotNull(photo3);

        assertNotNull(album3.getPhotos());

        albumPage = albumRepository.findAll(new PageRequest(0,3));
        albumList = albumPage.getContent();
        assertNotNull(albumPage.getContent());
        int albumListSize = albumPage.getContent().size();

        albumRepository.delete((long) albumListSize);
        albumPage = albumRepository.findAll(new PageRequest(0,2));
        assertTrue(albumPage.getContent().size() == albumListSize-1);

        // Album was deleted cascaded - all photos of album will deleted too
        photo3 = photoRepository.getPhotoByName("photo3");
        assertNull(photo3);
    }
}
