package it.epicode.helper;

import it.epicode.archi.Archivio;

import java.util.Scanner;

public class Helper {
    private Archivio archivio;
    private Scanner scanner;

    public Helper() {
        this.archivio = new Archivio();
        this.scanner = new Scanner(System.in);
    }


}
