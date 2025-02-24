package it.martino_gallozzi.giornale.dto;

import lombok.Data;

@Data
public class UtenteRegistration {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String citta;
    private String cap;
    private String email;
}
