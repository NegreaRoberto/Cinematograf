package model;

import java.util.Date;

public class Cinema {
    private int id;
    private String film;
    private int nrSala;
    private String nume;
    private int loc;
    private Date data;

    public Cinema(int id, String film, int nrSala, String nume, int loc, Date data) {
        this.id = id;
        this.film = film;
        this.nrSala = nrSala;
        this.nume = nume;
        this.loc = loc;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getFilm() {
        return film;
    }

    public int getNrSala() {
        return nrSala;
    }

    public String getNume() {
        return nume;
    }

    public int getLoc() {
        return loc;
    }

    public Date getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public void setNrSala(int nrSala) {
        this.nrSala = nrSala;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", film='" + film + '\'' +
                ", nrSala=" + nrSala +
                ", nume='" + nume + '\'' +
                ", loc='" + loc + '\'' +
                ", data=" + data +
                '}';
    }
}
