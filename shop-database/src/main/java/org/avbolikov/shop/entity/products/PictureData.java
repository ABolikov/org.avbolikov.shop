package org.avbolikov.shop.entity.products;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "picture_data")
public class PictureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob //для добавления Blob поля через hibernate
    @Type(type="org.hibernate.type.BinaryType") // для правильной работы PostgreSQL
    @Column(name = "data",
            nullable = false,
            length = 33554430) // для правильной hibernate-валидации в MySQL
    private byte[] data;

    public PictureData() {
    }

    public PictureData(byte[] data) {
        this.data = data;
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
}
