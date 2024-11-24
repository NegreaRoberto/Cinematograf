package controller;

import dao.CinemaDao;
import model.Cinema;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class CinemaController {
    private CinemaDao cinemaDao;

    public CinemaController(){
        cinemaDao = new CinemaDao(ConnectionController.getInstance().getConnection());

    }
    public void insertRezervari(String film, int nrSala, String nume, int loc, Date data){
        if(isLocOcupat(nrSala, loc, data)){
            System.out.println();
            return;
        }
        if(isSalaPlina(nrSala, loc, data)){
            System.out.println("Sala plina!");
            return;
        }
        if(data.before(new Date(System.currentTimeMillis()))){
            System.out.println("Data rezervare invalida");
            return;
        }
        cinemaDao.insertRezervari(film, nrSala, nume, loc,  data);
        System.out.println("Rezervarea filmului a fost efectuata");
    }

    public void afisareRezervari(String nume){
        List<Cinema> rezervariList = cinemaDao.getRezervariPersoana(nume);

        if(rezervariList.isEmpty()){
            System.out.println("Nu exista rezervari pe acest nume");
        }else{
            System.out.println("Rezervarile pentru persoana cu numele " + nume + ": ");
            for(Cinema rezervare : rezervariList){
                System.out.println(rezervare);
            }
        }
    }

    public List<Cinema> getAllRezervari(){
        return cinemaDao.getAllRezervari();
    }
    private boolean isLocOcupat(int nrSala, int loc, Date data){
        List<Cinema> rezervariInAceasiZiSiSala = cinemaDao.getRezervariInAceasiZiSiSala(nrSala, data);
        for (Cinema rezervare : rezervariInAceasiZiSiSala) {
            if (rezervare.getLoc() == loc) {
                System.out.println("Acest loc este deja rezervat in aceasta zi!");
                return true;
            }
        }
        return false;
    }
    private boolean isSalaPlina(int nrSala, int loc, Date data){
        int numarLocuriOcupate = cinemaDao.getNumarLocuriOcupateInSala(nrSala, data);
        return numarLocuriOcupate >= 20;
    }
    public int verificareCapacitate(int nrSala, Date dataRezervare){
        int capacitateMaxima = 20;
        int locuriOcupate = cinemaDao.getNumarLocuriOcupateInSala(nrSala, dataRezervare);
        int locuriRamase = capacitateMaxima - locuriOcupate;
        return locuriRamase;

    }

    public void stergereRezervare(int idRezervare){
        cinemaDao.stergeRezervare(idRezervare);
    }
    public void salveazaRezervariInFisierTxt(String numeFisier){
        List<Cinema> rezervari = cinemaDao.getAllRezervari();
        try(PrintWriter writer = new PrintWriter(numeFisier)){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dataOraSalvare = "Salvare: " + dateFormat.format(new java.util.Date());
            writer.println(dataOraSalvare);
            for(Cinema rezervare : rezervari){
                writer.println("Nume: " + rezervare.getNume());
                writer.println("Film: " + rezervare.getFilm());
                writer.println("Sala: " + rezervare.getNrSala());
                writer.println("Loc: " + rezervare.getLoc());
                writer.println("Data rezervare: " + rezervare.getData());
                writer.println();
            }
            System.out.println("Rezervarile au fost salvare in fisierul " + numeFisier);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
