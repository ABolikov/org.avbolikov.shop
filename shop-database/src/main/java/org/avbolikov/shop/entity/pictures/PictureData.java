package org.avbolikov.shop.entity.pictures;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "picture_data")
public class PictureData  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob //для добавления Blob поля через hibernate
    @Type(type="org.hibernate.type.BinaryType") // для правильной работы PostgreSQL
    @Column(name = "data",
            length = 33554430) // для правильной hibernate-валидации в MySQL
    private byte[] data;

    @Column(name = "file_name")
    private String fileName;

    public PictureData() {
    }

    public PictureData(byte[] data) {
        this.data = data;
    }

    public PictureData(String fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
