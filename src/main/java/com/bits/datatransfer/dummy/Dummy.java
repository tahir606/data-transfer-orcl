package com.bits.datatransfer.dummy;

import javax.persistence.*;

@Entity
@Table(name = "DUMMY_TABLE")
public class Dummy {

    @Id
    @GeneratedValue
    private long id;
    private String key;
    private String value;

    public Dummy() {

    }

    public Dummy(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dummy{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
