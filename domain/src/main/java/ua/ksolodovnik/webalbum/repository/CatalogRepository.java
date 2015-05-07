package ua.ksolodovnik.webalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ksolodovnik.webalbum.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Catalog getCatalogByName(String name);

}
