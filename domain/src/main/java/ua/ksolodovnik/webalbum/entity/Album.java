package ua.ksolodovnik.webalbum.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Album
 * @author ksolodovnik
 */
@Entity
public class Album implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String category;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Photo> photos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    public Album(){
        creationDate = new Date();
    }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
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

        Album album = (Album) o;

        if (!catalog.equals(album.catalog)) return false;
        if (!category.equals(album.category)) return false;
        if (!creationDate.equals(album.creationDate)) return false;
        if (!name.equals(album.name)) return false;
        if (!photos.equals(album.photos)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + photos.hashCode();
        result = 31 * result + catalog.hashCode();
        return result;
    }
}
