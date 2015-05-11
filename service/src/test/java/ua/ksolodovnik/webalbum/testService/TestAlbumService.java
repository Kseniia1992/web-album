package ua.ksolodovnik.webalbum.testService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import ua.ksolodovnik.webalbum.entity.Album;
import ua.ksolodovnik.webalbum.entity.Catalog;
import ua.ksolodovnik.webalbum.entity.Photo;
import ua.ksolodovnik.webalbum.service.AlbumService;
import ua.ksolodovnik.webalbum.service.PhotoService;
import ua.ksolodovnik.webalbum.util.InitDate;
import ua.ksolodovnik.webalbum.util.SaveImage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "file:web/src/main/webapp/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class TestAlbumService {

    private static Photo photo;
    private static Album album;
    private static Album album2;
    private static Catalog catalog;
    private static List<Photo> photoList;
    private static List<Album> albumList;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PhotoService photoService;

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
        album2.setCatalog(catalog2);
        album2.setCreationDate(InitDate.setDate());
        album2.setPhotos(photos);

    }

    @Before
    public void save(){
        albumService.saveAlbum(album);
        albumService.saveAlbum(album2);
    }

    @Test
    public void testSavePhoto(){
        album = albumService.getAlbumByName("animals");
        assertNotNull(album);

        photoList = album.getPhotos();
        assertNotNull(photoList);

        catalog = album.getCatalog();
        assertNotNull(catalog);
    }

    @Test
    public void testGetAll(){
        albumList = albumService.getAll();
        assertNotNull(albumService);

        album = albumList.get(albumList.size()-1);
        assertNotNull(album);
        assertNotNull(album.getId());
        assertNotNull((album.getCreationDate()));
        assertNotNull(album.getCatalog());
        assertNotNull(album.getPhotos());
        assertTrue(album.getName().equals("people"));
        assertTrue(album.getCatalog().getName().equals("catalog2"));
    }

    @Test
    public void testGetAlbumById(){
        album = albumService.getAlbumById(1L);
        assertNotNull(album);
        assertNotNull(album.getCreationDate());
        assertNotNull(album.getPhotos());
        assertNotNull(album.getCatalog());
        assertTrue(album.getName().equals("animals"));
    }

    @Test
    public void testGetAlbumByName(){
        album = albumService.getAlbumByName("animals");
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
        albumList = albumService.getLatestAlbum();
        assertNotNull(albumList);

        album2 = albumList.get(albumList.size()-1);
        album = albumService.getAlbumByName("animals");

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
        albumService.saveAlbum(album3);
        photoService.savePhoto(photo3);

        photo3 = photoService.getPhotoByName("photo3");
        assertNotNull(photo3);

        assertNotNull(album3.getPhotos());

        albumList = albumService.getAll();
        assertNotNull(albumList);
        int albumListSize = albumList.size();

        albumService.deleteAlbumById((long) albumListSize);
        albumList = albumService.getAll();
        assertTrue(albumList.size() == albumListSize-1);

        // Album was deleted cascaded - all photos of album will deleted too
        photo3 = photoService.getPhotoByName("photo3");
        assertNull(photo3);
    }
}
