package it.epicode.helper;

import it.epicode.archi.Archivio;
import it.epicode.catalogo.ElementoCatalogo;
import it.epicode.catalogo.Libro;
import it.epicode.catalogo.Rivista;
import it.epicode.catalogo.Periodicita;
import it.epicode.eccezioni.ElementoNonTrovatoException;

import java.util.Scanner;

public class Helper {
    private Archivio archivio;
    private Scanner scanner;

    public Helper() {
        this.archivio = new Archivio();
        this.scanner = new Scanner(System.in);
    }

    public void startMenu() {
        while (true) {
            System.out.println("------- MENU -------");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Aggiungi rivista");
            System.out.println("3. Cerca elemento per ISBN");
            System.out.println("4. Rimuovi elemento per ISBN");
            System.out.println("5. Ricerca per anno di pubblicazione");
            System.out.println("6. Ricerca per autore");
            System.out.println("7. Ricerca per genere");
            System.out.println("8. Aggiorna elemento esistente per ISBN");
            System.out.println("9. Statistiche catalogo");
            System.out.println("0. Esci");

            System.out.print("Seleziona una opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();  // Consuma il newline rimasto

            try {
                switch (scelta) {
                    case 1:
                        aggiungiLibro();
                        break;
                    case 2:
                        aggiungiRivista();
                        break;
                    case 3:
                        cercaElemento();
                        break;
                    case 4:
                        rimuoviElemento();
                        break;
                    case 5:
                        ricercaPerAnno();
                        break;
                    case 6:
                        ricercaPerAutore();
                        break;
                    case 7:
                        ricercaPerGenere();
                        break;
                    case 8:
                        aggiornaElemento();
                        break;
                    case 9:
                        archivio.stampaStatistiche();
                        break;
                    case 0:
                        System.out.println("Arrivederci!");
                        return;
                    default:
                        System.out.println("Errore: Scelta non valida.");
                }
            } catch (ElementoNonTrovatoException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    private void aggiungiLibro() {
        System.out.println("Inserisci il codice ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Inserisci il Titolo: ");
        String titolo = scanner.nextLine();
        System.out.println("Anno di pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.println("Numero di Pagine: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();  // Consuma il newline rimasto
        System.out.println("Inserisci Autore: ");
        String autore = scanner.nextLine();
        System.out.println("Inserisci Genere: ");
        String genere = scanner.nextLine();

        Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);
        archivio.aggiungiElemento(libro);
        System.out.println("Libro aggiunto con successo!");
    }

    private void aggiungiRivista() {
        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Inserisci Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Inserisci Anno di Pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.print("Inserisci Numero di Pagine: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserisci Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
        String periodicitaStr = scanner.nextLine().toUpperCase();

        try {
            Periodicita periodicita = Periodicita.valueOf(periodicitaStr);
            Rivista rivista = new Rivista(isbn, titolo, anno, pagine, periodicita);
            archivio.aggiungiElemento(rivista);
            System.out.println("Rivista aggiunta con successo!");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: Periodicità non valida.");
        }
    }

    private void cercaElemento() throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN dell'elemento da cercare: ");
        String isbn = scanner.nextLine();
        ElementoCatalogo elemento = archivio.cercaElemento(isbn);
        System.out.println("Elemento trovato: " + elemento);
    }

    private void rimuoviElemento() throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere: ");
        String isbn = scanner.nextLine();
        archivio.rimuoviElemento(isbn);
        System.out.println("Elemento rimosso con successo.");
    }

    private void ricercaPerAnno() {
        System.out.println("Inserisci l'anno di pubblicazione: ");
        int anno = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Risultati: " + archivio.ricercaPerAnno(anno));
    }

    private void ricercaPerAutore() {
        System.out.println("Inserisci l'autore: ");
        String autore = scanner.nextLine();
        System.out.println("Risultati: " + archivio.ricercaPerAutore(autore));
    }

    private void ricercaPerGenere() {
        System.out.println("Inserisci il genere: ");
        String genere = scanner.nextLine();
        System.out.println("Risultati: " + archivio.ricercaPerGenere(genere));
    }

    private void aggiornaElemento() throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN dell'elemento da aggiornare: ");
        String isbn = scanner.nextLine();
        System.out.println("Inserisci i nuovi dati dell'elemento.");

        System.out.println("Se è un libro, inserisci autore e genere. Se è una rivista, inserisci periodicità.");
        System.out.println("1. Libro");
        System.out.println("2. Rivista");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            aggiungiLibro();
        } else if (tipo == 2) {
            aggiungiRivista();
        } else {
            System.out.println("Scelta non valida.");
        }
    }
}
