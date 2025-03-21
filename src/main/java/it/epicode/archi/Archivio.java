package it.epicode.archi;

import it.epicode.catalogo.ElementoCatalogo;
import it.epicode.catalogo.Libri;
import it.epicode.eccezioni.ElementoNonTrovatoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archivio {
    private List<ElementoCatalogo> elementi = new ArrayList<>();

    public void aggiungiElemento(ElementoCatalogo elemento) throws ElementoNonTrovatoException {
        if (elementi.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            throw new ElementoNonTrovatoException(elemento.getIsbn());
        } else {
            elementi.add(elemento);
        }
    }

    public ElementoCatalogo cercaElemento(String isbn) {
        return elementi.stream().filter(e -> e.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    private static void aggiungiLibro(Archivio archivio, Scanner scanner) throws ElementoNonTrovatoException {
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
            rimuoviLibro(elemento);
            System.out.println("Elemento rimosso.");
        } else {
            System.out.println("Elemento non trovato.");
        }
    }

    private static void ricercaPerAnno (Archivio archivio, Scanner scanner) {
        System.out.println("Anno di pubblicazione: ");
        int anno = scanner.nextInt();
        List<ElementoCatalogo> risultati = annoPubblicazione(anno);
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

    private static void aggiornaElemento (Archivio archivio, Scanner scanner) throws ElementoNonTrovatoException, ElementoNonTrovatoException {
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
