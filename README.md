# Esercizio Java: Pizzeria Security

### Continuo della repository spring-la-mia-pizzeria-webapi

## Descrizione

Stiamo sviluppando un’applicazione web per gestire una pizzeria, con funzionalità complete di gestione delle pizze, offerte speciali, ingredienti e sicurezza.

L’app permette di visualizzare un elenco di pizze con le relative informazioni (id, nome, descrizione, foto, prezzo) e di vedere i dettagli di ciascuna pizza tramite una pagina dedicata. È possibile filtrare le pizze in base al titolo.

Si possono creare nuove pizze attraverso un form con validazione dei dati, modificarle o eliminarle direttamente dall’elenco. Le pizze possono essere associate a offerte speciali con date di inizio, fine e titoli, visualizzabili nella pagina di dettaglio, e gestite con funzioni di creazione e modifica.

Inoltre, ogni pizza può essere collegata a più ingredienti, con pagine dedicate per la gestione degli ingredienti stessi (elenco, creazione, cancellazione) e la possibilità di assegnarli alle pizze in fase di creazione o modifica.

Il progetto espone anche API REST per tutte le operazioni CRUD sulle pizze, utilizzabili da strumenti esterni come Postman.

Infine, l’applicazione è protetta tramite autenticazione con ruoli utente (USER e ADMIN): gli utenti USER possono accedere solo alla lista e ai dettagli delle pizze, mentre gli ADMIN hanno permessi completi di gestione su tutte le funzionalità.
