package dao;

import model.Cinema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CinemaDao {
    private Connection connection;
    private PreparedStatement getAllRezervari;
    private PreparedStatement insertRezervari;
    private PreparedStatement stergeRezervare;
    private PreparedStatement getRezervariPersoana;
    private PreparedStatement getNumarLocuriOcupateInSala;
    private PreparedStatement getRezervariInAceasiZiSiSala;

    public CinemaDao(Connection connection) {
        this.connection = connection;
        try {
            insertRezervari = connection.prepareStatement("INSERT INTO rezervari VALUES (null, ?, ?, ?, ?, ?)");
            getAllRezervari = connection.prepareStatement("SELECT * FROM rezervari");
            stergeRezervare = connection.prepareStatement("DELETE FROM rezervari WHERE id = ?");
            getRezervariPersoana = connection.prepareStatement("SELECT * FROM rezervari WHERE nume = ?");
            getNumarLocuriOcupateInSala = connection.prepareStatement("SELECT COUNT(*) FROM rezervari WHERE nrSala = ? AND data = ?");
            getRezervariInAceasiZiSiSala = connection.prepareStatement("SELECT * FROM rezervari WHERE nrSala = ? AND data = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertRezervari(String film, int nrSala, String nume, int loc, Date data) {
        try {
            insertRezervari.setString(1, film);
            insertRezervari.setInt(2, nrSala);
            insertRezervari.setString(3, nume);
            insertRezervari.setInt(4, loc);
            insertRezervari.setDate(5, data);

            insertRezervari.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void stergeRezervare(int idRezervare) {
        try{
            stergeRezervare.setInt(1, idRezervare);

            stergeRezervare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cinema> getAllRezervari() {
        ArrayList<Cinema> list = new ArrayList<Cinema>();
        try {
            ResultSet resultSet = getAllRezervari.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String film = resultSet.getString(2);
                int nrSala = resultSet.getInt(3);
                String nume = resultSet.getString(4);
                int loc = resultSet.getInt(5);
                Date data = resultSet.getDate(6);
                Cinema cinema = new Cinema(id, film, nrSala, nume, loc, data);
                list.add(cinema);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Cinema> getRezervariPersoana(String nume) {
        List<Cinema> rezervariList = new ArrayList<>();

        try{
            getRezervariPersoana.setString(1, nume);
            ResultSet resultSet = getRezervariPersoana.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String film = resultSet.getString("film");
                int nrSala = resultSet.getInt("nrSala");
                String numeRezervare = resultSet.getString("nume");
                int loc = resultSet.getInt("loc");
                Date data = resultSet.getDate("data");

                Cinema rezervare = new Cinema(id, film, nrSala, numeRezervare, loc, data);
                rezervariList.add(rezervare);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rezervariList;
    }

    public int getNumarLocuriOcupateInSala(int nrSala, Date data) {
        int numarLocuriOcupate = 0;
        try{
            getNumarLocuriOcupateInSala.setInt(1, nrSala);
            getNumarLocuriOcupateInSala.setDate(2, data);

            try (ResultSet resultSet = getNumarLocuriOcupateInSala.executeQuery()) {
                if (resultSet.next()) {
                    numarLocuriOcupate = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numarLocuriOcupate;
    }

    public List<Cinema> getRezervariInAceasiZiSiSala(int nrSala, Date dataRezervare) {
        List<Cinema> rezervariInAceasiZiSiSala = new ArrayList<>();

        try {
            getRezervariInAceasiZiSiSala.setInt(1, nrSala);
            getRezervariInAceasiZiSiSala.setDate(2, dataRezervare);

            ResultSet resultSet = getRezervariInAceasiZiSiSala.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String film = resultSet.getString(2);
                int sala = resultSet.getInt(3);
                String nume = resultSet.getString(4);
                int loc = resultSet.getInt(5);
                Date data = resultSet.getDate(6);
                Cinema rezervare = new Cinema(id, film, sala, nume, loc, data);
                rezervariInAceasiZiSiSala.add(rezervare);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rezervariInAceasiZiSiSala;
    }
}
