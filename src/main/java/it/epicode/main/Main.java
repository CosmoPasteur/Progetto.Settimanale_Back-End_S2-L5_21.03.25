package it.epicode.main;


import it.epicode.archi.Archivio;
import it.epicode.catalogo.ElementoCatalogo;
import it.epicode.catalogo.Libri;
import it.epicode.eccezioni.ElementoNonTrovatoException;
import it.epicode.eccezioni.IsbnGiaEsistenteException;

import java.util.List;
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
                        rimuoviLibro(archivio, scanner);
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
            } catch (IsbnGiaEsistenteException | ElementoNonTrovatoException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

    }

    private static void aggiungiLibro(Archivio archivio, Scanner scanner) throws IsbnGiaEsistenteException {
        System.out.print("Inserisci il tipo di elemento ( Libro / Rivista ):");
        String tipo = scanner.nextLine();

        System.out.println("Inserisci il codice ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Inserisci il Titolo: ");
        String titolo = scanner.nextLine();
        System.out.println("Anno di pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.println("Numero di Pagine: ");
        int pagine = scanner.nextInt();
    }

    private static void cercaLibro(Archivio archivio, Scanner scanner) throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN del libro da cercare: ");
        String isbn = scanner.nextLine();
        ElementoCatalogo elemento = archivio.cercaElemento(isbn);
        if (elemento != null) {
            System.out.println("Elemento trovato: " + elemento.toString());
        } else {
            System.out.println("Elemento non trovato.");
        }
    }

    private static void rimuoviLibro(Archivio archivio, Scanner scanner) throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN del libro da rimuovere: ");
        String isbn = scanner.nextLine();
        ElementoCatalogo elemento = archivio.cercaElemento(isbn);
        if (elemento != null) {
            archivio.rimuoviElemento(elemento);
            System.out.println("Elemento rimosso.");
        } else {
            System.out.println("Elemento non trovato.");
        }
    }

    private static void ricercaPerAnno (Archivio archivio, Scanner scanner) {
        System.out.println("Anno di pubblicazione: ");
        int anno = scanner.nextInt();
        List<ElementoCatalogo> risultati = archivio.annoPubblicazione(anno);
        System.out.println("Risultati: " + risultati);
    }

    private static void ricercaPerAutore(Archivio archivio, Scanner scanner) {
        System.out.println("Autore: ");
        String autore = scanner.nextLine();
        List<Libri> risultati = archivio.ricercaPerAutore(autore);
        System.out.println("Risultati: " + risultati);
    }

    private static void ricercaPerGenere (Archivio archivio, Scanner scanner) {
        System.out.print("Genere: ");
        String genere = scanner.nextLine();
        List<Libri> risultati = archivio.ricercaPerGenere(genere);
        System.out.println("Risultati: " + risultati);
    }

    private static void aggiornaElemento (Archivio archivio, Scanner scanner) throws ElementoNonTrovatoException, IsbnGiaEsistenteException {
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        aggiungiLibro(archivio, scanner);
        archivio.aggiungiElemento(isbn, archivio.cercaLibro(isbn));
        System.out.println("Elemento aggiornato.");
    }

    private static void statisticheCatalogo(Archivio archivio) {
        System.out.println("Numero di libri presenti: " + archivio.getElementi().stream().filter(e -> e instanceof Libri).count());

    }


}
