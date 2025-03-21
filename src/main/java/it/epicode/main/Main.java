package it.epicode.main;


import it.epicode.archi.Archivio;
import it.epicode.catalogo.ElementoCatalogo;
import it.epicode.catalogo.Libri;
import it.epicode.eccezioni.ElementoNonTrovatoException;

import java.util.List;
import java.util.Scanner;

import static it.epicode.archi.Archivio.*;

public class Main {
    public static void main(String[] args) {


        while (true) {
            System.out.println("------- MENU -------");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Cerca libro");
            System.out.println("3. Rimuovi libro tramite codice ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Ricerca per genere");
            System.out.println(("7. Aggiorna elemento esistente tramite ISBN"));
            System.out.println("8. Statistiche catalogo");
            System.out.println("0. Esci");

            System.out.print("Seleziona una opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1:
                        aggiungiLibro(archivio, scanner);
                        break;
                    case 2:
                        cercaLibro(archivio, scanner);
                        break;
                    case 3:
                        rimuoviElemento(archivio, scanner);
                        break;
                    case 4:
                        ricercaPerAnno(archivio, scanner);
                        break;
                    case 5:
                        ricercaPerAutore(archivio, scanner);
                        break;
                    case 6:
                        ricercaPerGenere(archivio, scanner);
                        break;
                    case 7:
                        aggiornaElemento(archivio, scanner);
                        break;
                    case 8:
                        statisticheCatalogo(archivio);
                        break;
                    case 0:
                        System.out.println("Arrivederci!");
                        return;
                    default:
                        System.out.println("Errore: Scelta non valida.");


                }
            } catch ( ElementoNonTrovatoException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

    }




}
