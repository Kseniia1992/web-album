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
import ua.ksolodovnik.webalbum.entity.Catalog;
import ua.ksolodovnik.webalbum.repository.CatalogRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "file:domain/src/main/resources/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class TestCatalogRepository {

    private static Catalog catalog;
    private static List<Catalog> catalogList;
    private static Page<Catalog> catalogPage;

    @Autowired
    private CatalogRepository catalogRepository;

    @BeforeClass
    public static void init(){
        catalog = new Catalog();
        catalogList = new ArrayList<>();

        catalog.setName("catalog1");
        catalog.setCategory("nature");
    }

    @Before
    public void save(){
        catalogRepository.save(catalog);
    }

    @Test
    public void testSaveCatalog(){
        catalog = catalogRepository.getCatalogByName("catalog1");
        assertNotNull(catalog);
    }

    @Test
    public void testGetAll(){
        catalogPage = catalogRepository.findAll(new PageRequest(0,1));
        catalogList = catalogPage.getContent();
        assertNotNull(catalogList);

        catalog = catalogList.get(catalogList.size()-1);
        assertNotNull(catalog);
        assertNotNull(catalog.getId());
        assertTrue(catalog.getName().equals("catalog1"));
        assertTrue(!catalog.getName().equals("ccc"));
        assertTrue(catalog.getCategory().equals("nature"));
    }

    @Test
    public void testGetCatalogById(){
        catalog = catalogRepository.findOne(1L);
        assertNotNull(catalog);
        assertTrue(catalog.getName().equals("catalog1"));
        assertTrue(!catalog.getName().equals("cat"));
    }

    @Test
    public void testGetCatalogByName(){
        catalog = catalogRepository.getCatalogByName("catalog1");
        assertNotNull(catalog);
        assertNotNull(catalog.getId());
        assertTrue(catalog.getName().equals("catalog1"));
    }

    @Test
    public void testDeleteCatalogById(){
        Catalog catalog1 = new Catalog();
        catalog1.setName("cataloggg");
        catalogRepository.save(catalog1);

        catalogPage = catalogRepository.findAll(new PageRequest(0,2));
        int catalogListSize = catalogPage.getContent().size();

        catalogRepository.delete((long) catalogListSize);
        catalogPage = catalogRepository.findAll(new PageRequest(0,1));

        assertTrue(catalogPage.getContent().size() == catalogListSize-1);
    }
}
