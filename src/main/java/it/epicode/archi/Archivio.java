package it.epicode.archi;

import it.epicode.catalogo.ElementoCatalogo;
import it.epicode.catalogo.Libro;
import it.epicode.catalogo.Rivista;
import it.epicode.eccezioni.ElementoNonTrovatoException;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private List<ElementoCatalogo> elementi = new ArrayList<>();

    // Aggiunta di un elemento con controllo duplicati
    public void aggiungiElemento(ElementoCatalogo elemento) {
        if (elementi.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            throw new IllegalArgumentException("Elemento con ISBN " + elemento.getIsbn() + " già presente.");
        }
        elementi.add(elemento);
    }

    // Ricerca per ISBN con eccezione custom
    public ElementoCatalogo cercaElemento(String isbn) throws ElementoNonTrovatoException {
        return elementi.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato."));
    }

    // Rimozione di un elemento dato un codice ISBN
    public void rimuoviElemento(String isbn) throws ElementoNonTrovatoException {
        ElementoCatalogo elemento = cercaElemento(isbn);
        elementi.remove(elemento);
    }

    // Ricerca per anno pubblicazione
    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return elementi.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // Ricerca per autore (solo per i libri)
    public List<Libro> ricercaPerAutore(String autore) {
        return elementi.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // Ricerca per genere (aggiunta perché era citata)
    public List<Libro> ricercaPerGenere(String genere) {
        return elementi.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }

    // Aggiornamento di un elemento dato l'ISBN
    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws ElementoNonTrovatoException {
        rimuoviElemento(isbn);
        aggiungiElemento(nuovoElemento);
    }

    // Statistiche del catalogo
    public void stampaStatistiche() {
        long numLibri = elementi.stream().filter(e -> e instanceof Libro).count();
        long numRiviste = elementi.stream().filter(e -> e instanceof Rivista).count();
        ElementoCatalogo maxPagine = elementi.stream().max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine)).orElse(null);
        double mediaPagine = elementi.stream().mapToInt(ElementoCatalogo::getNumeroPagine).average().orElse(0);

        System.out.println("Numero totale di libri: " + numLibri);
        System.out.println("Numero totale di riviste: " + numRiviste);
        System.out.println("Elemento con più pagine: " + (maxPagine != null ? maxPagine : "Nessun elemento"));
        System.out.println("Media delle pagine: " + mediaPagine);
    }
}
