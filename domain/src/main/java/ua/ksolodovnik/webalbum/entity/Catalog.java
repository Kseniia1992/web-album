package ua.ksolodovnik.webalbum.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Catalog
 * @author ksolodovnik
 */
@Entity
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String category;

    @OneToMany(mappedBy = "catalog")
    private List<Album> albums;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        if (!albums.equals(catalog.albums)) return false;
        if (!category.equals(catalog.category)) return false;
        if (!name.equals(catalog.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + albums.hashCode();
        return result;
    }
}
