package view;

import controller.CinemaController;
import model.Cinema;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class CinemaOperations {
    private static CinemaController cinemaController = new CinemaController();
    private static Scanner scanner = new Scanner(System.in);
    private CinemaOperations(){

    }

    public static void cinemaOperations(){
        while(true){
        System.out.println();
        System.out.println("Ce doriti sa faceti?");
        System.out.println("Comenzi: ");
        System.out.println("Rezervare");
        System.out.println("Verificare Capacitate");
        System.out.println("Afisare Rezervari");
        System.out.println("Sterge Rezervare");
        System.out.println("Salveaza in fisier txt");
        System.out.println("LogOut");
        String cmd = scanner.nextLine();

        switch (cmd){
            case "Rezervare":
                System.out.println("Filme disponibile: ");
                System.out.println("Oppenheimer");
                System.out.println("Interstellar");
                System.out.println("Tenet");
                System.out.println("The dark knight");
                System.out.println("Memento");
                String film = scanner.nextLine();
                int nrSala = 0;
                if(film.equals("Oppenheimer")){
                    System.out.println("Alegeti una dintre salile disponibile: 1, 2");
                    nrSala = Integer.parseInt(scanner.nextLine());
                    if (nrSala < 1 || nrSala > 2){
                        System.out.println("Sala invalida");
                        break;
                    }
                } else if (film.equals("Interstellar")) {
                    System.out.println("Alegeti una dintre salile disponibile: 3, 4");
                    nrSala = Integer.parseInt(scanner.nextLine());
                    if (nrSala < 3 || nrSala > 4){
                        System.out.println("Sala invalida");
                        break;
                    }
                } else if (film.equals("Tenet")) {
                    System.out.println("Alegeti una dintre salile disponibile: 5, 6");
                    nrSala = Integer.parseInt(scanner.nextLine());
                    if (nrSala < 5 || nrSala > 6){
                        System.out.println("Sala invalida");
                        break;
                    }
                } else if (film.equals("The dark knight")) {
                    System.out.println("Alegeti una dintre salile disponibile: 7, 8");
                    nrSala = Integer.parseInt(scanner.nextLine());
                    if (nrSala < 7 || nrSala > 8){
                        System.out.println("Sala invalida");
                        break;
                    }
                } else if (film.equals("Memento")) {
                    System.out.println("Alegeti una dintre salile disponibile: 9");
                    nrSala = Integer.parseInt(scanner.nextLine());
                    if (nrSala != 9){
                        System.out.println("Sala invalida");
                        break;
                    }
                } else {
                    System.out.println("Sala invalida, numarul salii trebuie sa fie intre 1 si 9");
                    break;
                }
                System.out.println("Numele pe care se face rezervarea: ");
                String nume = scanner.nextLine();
                System.out.println("Loc: ");
                int loc = Integer.parseInt(scanner.nextLine());
                if (loc < 1 || loc > 20){
                    System.out.println("Loc invalid");
                }
                System.out.println("Data (YYYY-MM-DD): ");
                Date data = Date.valueOf(scanner.nextLine());
                cinemaController.insertRezervari(film, nrSala, nume, loc, data);
                break;
            case "Verificare Capacitate":
                System.out.println("Introduceti numarul salii si data pentru verificarea locurilor libere");
                System.out.println("Sala: ");
                nrSala = scanner.nextInt();
                scanner.nextLine();
                if (nrSala < 1 || nrSala > 9){
                    System.out.println("Numarul salii trebuie sa fie intre 1 si 9");
                }
                System.out.println("Data: ");
                Date dataRezervare = Date.valueOf(scanner.nextLine());
                int locuriRamase = cinemaController.verificareCapacitate(nrSala, dataRezervare);
                System.out.println("Numarul de locuri libere ramase in sala " + nrSala + ": " + locuriRamase);
                break;
            case "LogOut":
                System.out.println("V-ati deconectat");
                UserOperations.userOperations();
                break;
            case "Afisare Rezervari":
                System.out.println("Introduceti numele pentru afisarea rezervarilor");
                String numeClient = scanner.nextLine();
                cinemaController.afisareRezervari(numeClient);
                break;
            case "getAllRezervari":
                    List<Cinema> list = cinemaController.getAllRezervari();
                    for(Cinema c : list){
                        System.out.println(c);
                    }
                break;
            case "Sterge Rezervare":
                System.out.println("Introduceti id-ul rezervarii pentru stergere");
                int idRezervare = scanner.nextInt();
                scanner.nextLine();
                cinemaController.stergereRezervare(idRezervare);
                System.out.println("Rezervarea cu id-ul " + idRezervare + " a fost stearsa");
                break;
            case "Salveaza in fisier txt":
                System.out.println("introduceti numele fisierului txt pentru salvarea rezervarilor");
                String numeFisier = scanner.nextLine();
                cinemaController.salveazaRezervariInFisierTxt(numeFisier);
                break;
            default:
                    System.out.println("Comanda invalida. Te rog sa alegi o optiune din Meniu");
                break;
        }
        }
    }
}
