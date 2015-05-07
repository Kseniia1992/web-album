package ua.ksolodovnik.webalbum.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Photo
 * @author ksolodovnik
 */
@Entity
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Lob
    private byte[] image;

    @Column(name = "addition_date")
    private Date additionDate;

    @ManyToMany(mappedBy = "photos")
    private List<Album> albums;

    public Photo(){
        additionDate = new Date();
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (!additionDate.equals(photo.additionDate)) return false;
        if (!albums.equals(photo.albums)) return false;
        if (!Arrays.equals(image, photo.image)) return false;
        if (!name.equals(photo.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + additionDate.hashCode();
        result = 31 * result + albums.hashCode();
        return result;
    }
}
