package it.epicode.main;

import it.epicode.archi.Archivio;
import it.epicode.catalogo.Libri;
import it.epicode.eccezioni.IsbnGiaEsistenteException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------- MENU -------");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Cerca libro");
            System.out.println("3. Rimuovi libro tramite codice ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Ricerca per genere");
            System.out.println(("7. Aggiorna elemento esistente tramite ISBN"));
            System.out.println("Statistiche catalogo");
            System.out.println("0. Esci");

            System.out.print("Seleziona una opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1:
                        System.out.print("Inserisci ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Inserisci titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Inserisci anno di pubblicazione: ");
                        int annoPubblicazione = scanner.nextInt();
                        System.out.print("Inserisci numero di pagine: ");
                        int numeroPagine = scanner.nextInt();
                        System.out.print("Inserisci autore: ");
                        String autore = scanner.nextLine();
                        System.out.print("Inserisci genere: ");
                        String genere = scanner.nextLine();
                        Libri libro = new Libri(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
                        archivio.aggiungiElemento(libro);
                        System.out.println("Libro aggiunto con successo.");
                        break;
                }
            }
        }

    }
}
