package org.eric.telegrambots.model.pttnotify;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;

    @Column(name = "name")
    private int name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
