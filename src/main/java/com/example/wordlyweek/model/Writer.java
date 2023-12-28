
package com.example.wordlyweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.wordlyweek.model.Magazine;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writerid")
    private int writerId;

    @Column(name = "writername")
    private String writerName;

    @Column(name = "bio")
    private String bio;

    @ManyToMany
    @JoinTable(name = "magazine_writer", joinColumns = @JoinColumn(name = "writerid"), inverseJoinColumns = @JoinColumn(name = "id"))
    @JsonIgnoreProperties("writers")
    private List<Magazine> magazines;

    public Writer() {
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }
}
