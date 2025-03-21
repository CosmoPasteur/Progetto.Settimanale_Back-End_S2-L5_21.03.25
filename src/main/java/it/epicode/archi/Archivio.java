package it.epicode.archi;

import it.epicode.catalogo.ElementoCatalogo;

import java.util.ArrayList;
import java.util.List;

public class Archivio {
    private List<ElementoCatalogo> elementi = new ArrayList<>();

    public void aggiungiElemento(ElementoCatalogo elemento) throws IsbnGiaEsistenteException {
        if (elementi.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            throw new IsbnGiaEsistenteException(elemento.getIsbn());
        } else {
            elementi.add(elemento);
        }
    }

    public ElementoCatalogo cercaElemento(String isbn) {
        return elementi.stream().filter(e -> e.getIsbn().equals(isbn)).findFirst().orElse(null);
    }


}
