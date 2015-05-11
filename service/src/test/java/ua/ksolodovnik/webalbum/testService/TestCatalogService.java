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
import ua.ksolodovnik.webalbum.service.CatalogService;
import ua.ksolodovnik.webalbum.service.exception.CatalogException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "file:domain/src/main/resources/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class TestCatalogService {

    private static Catalog catalog;
    private static List<Catalog> catalogList;

    @Autowired
    private CatalogService catalogService;

    @BeforeClass
    public static void init(){
        catalog = new Catalog();
        catalogList = new ArrayList<>();

        catalog.setName("catalog1");
        catalog.setCategory("Weekend");
    }

    @Before
    public void save(){
        catalogService.saveCatalog(catalog);
    }

    @Test
    public void testSaveCatalog(){
        catalog = catalogService.getCatalogByName("catalog1");
        assertNotNull(catalog);
    }

    @Test
    public void testGetAll(){
        catalogList = catalogService.getAllPageble();
        assertNotNull(catalogList);

        catalog = catalogList.get(catalogList.size()-1);
        assertNotNull(catalog);
        assertNotNull(catalog.getId());
        assertTrue(catalog.getName().equals("catalog1"));
        assertTrue(!catalog.getName().equals("ccc"));
        assertTrue(catalog.getCategory().equals("Weekend"));
    }

    @Test
    public void testGetCatalogById(){
        catalog = catalogService.getCatalogById(1L);
        assertNotNull(catalog);
        assertTrue(catalog.getName().equals("catalog1"));
        assertTrue(!catalog.getName().equals("cat"));
    }

    @Test
    public void testGetCatalogByName(){
        catalog = catalogService.getCatalogByName("catalog1");
        assertNotNull(catalog);
        assertNotNull(catalog.getId());
        assertTrue(catalog.getName().equals("catalog1"));
    }

    @Test
    public void testDeleteCatalogById(){
        Catalog catalog1 = new Catalog();
        catalog1.setName("cataloggg");
        catalogService.saveCatalog(catalog1);

        catalogList = catalogService.getAllPageble();
        int catalogListSize = catalogList.size();

        catalogService.deleteCatalogById((long) catalogListSize);
        catalogList = catalogService.getAllPageble();

        assertTrue(catalogList.size() == catalogListSize-1);
    }

    @Test
    public void testAddAlbumToCatalog() throws CatalogException {
        Album album = new Album();
        album.setName("NewYear");
        album.setCategory("Weekend");
        catalogService.addAlbumToCatalog(catalog, album);
        assertNotNull(catalog.getAlbums());

        List<Album> albumList = catalog.getAlbums();
        album = albumList.get(albumList.size()-1);
        assertNotNull(album.getId());
        assertTrue(album.getName().equals("NewYear"));

        catalog = album.getCatalog();
        assertNotNull(catalog);
        assertNotNull(catalog.getId());
        assertTrue(catalog.getName().equals("catalog1"));
    }

    /**
     * Catalog category not the same as Album category.
     * Exception expected.
     */
    @Test(expected = CatalogException.class)
    public void testAddAlbumToCatalogExc() throws CatalogException {
        Album album = new Album();
        album.setName("NewYear");
        album.setCategory("Event");
        catalogService.addAlbumToCatalog(catalog, album);
        assertNull(catalog.getAlbums());
    }
}