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
import ua.ksolodovnik.webalbum.entity.Photo;
import ua.ksolodovnik.webalbum.repository.PhotoRepository;
import ua.ksolodovnik.webalbum.util.InitDate;
import ua.ksolodovnik.webalbum.util.SaveImage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "file:domain/src/main/resources/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class TestPhotoRepository {

    private static Photo photo1;
    private static Photo photo2;
    private static List<Photo> photoList;
    private static Page<Photo> photoPage;

    @Autowired
    private PhotoRepository photoRepository;

    @BeforeClass
    public static void init(){
        photo1 = new Photo();
        photo2 = new Photo();
        photoList = new ArrayList<>();

        photo1.setName("photo1");
        photo1.setImage(SaveImage.saveImg());

        photo2.setName("photo2");
        photo2.setImage(SaveImage.saveImg());
        photo2.setAdditionDate(InitDate.setDate());
    }

    @Before
    public void save(){
        photoRepository.save(photo1);
        photoRepository.save(photo2);
    }

    @Test
    public void testSavePhoto(){
        photo1 = photoRepository.getPhotoByName("photo1");
        assertNotNull(photo1);
    }

    @Test
    public void testGetAll(){
        photoPage =  photoRepository.findAll(new PageRequest(0,1));
        photoList = photoPage.getContent();
        assertNotNull(photoList);
        assertTrue(photoList.size() == 1);

        photo1 = photoList.get(photoList.size()-1);
        assertNotNull(photo1);
        assertNotNull(photo1.getId());
        assertNotNull(photo1.getImage());
        assertNotNull(photo1.getAdditionDate());
        assertTrue(photo1.getName().equals("photo1"));
        assertTrue(!photo1.getName().equals("photo2"));
    }

    @Test
    public void testGetPhotoById(){
        photo1 = photoRepository.findOne(1L);
        assertNotNull(photo1);
        assertTrue(photo1.getName().equals("photo1"));
        assertTrue(!photo1.getName().equals("photo2"));
        assertNotNull(photo1.getImage());
        assertNotNull(photo1.getAdditionDate());
    }

    @Test
    public void testGetPhotoByName(){
        photo1 = photoRepository.getPhotoByName("photo1");
        assertNotNull(photo1);
        assertNotNull(photo1.getId());
        assertNotNull(photo1.getAdditionDate());
        assertNotNull(photo1.getImage());
        assertTrue(photo1.getName().equals("photo1"));
    }

    @Test
    public void testGetLatestPhoto(){
        photoList = photoRepository.getLatestPhoto();
        assertNotNull(photoList);

        photo2 = photoList.get(photoList.size()-1);
        photo1 = photoRepository.getPhotoByName("photo1");

        assertTrue(photo2.getAdditionDate().after(photo1.getAdditionDate()));
    }

    @Test
    public void testDeletePhotoById(){
        Photo photo3 = new Photo();
        photo3.setName("photo3");
        photoRepository.save(photo3);

        photoPage = photoRepository.findAll(new PageRequest(0,3));
        int photoListSize = photoPage.getContent().size();

        photoRepository.delete((long) photoListSize);
        photoPage = photoRepository.findAll(new PageRequest(0,2));
        assertTrue(photoPage.getContent().size() == photoListSize-1);
    }
}
