# UDP_comunication

Questo progetto simula una comunicazione **UDP** tra un Client e un Server. Il Client permette di digitare messaggi da tastiera e inviarli, mentre il Server resta in ascolto e stampa quello che riceve insieme all'indirizzo IP di chi ha scritto.

## Come funziona

1. **UDP Puro**: La comunicazione è a senso unico. Il Client invia e non aspetta conferme.
2. **Chiusura**: Se scrivi la parola `stop` dal Client, sia il Client che il Server si spengono automaticamente.
3. **Pulizia dati**: Il Server usa `getLength()` per evitare di leggere byte vuoti e mostrare messaggi sporchi.

## Classi del Progetto

### Pacchetto `client`
*   **Client.java**: Contiene la logica per creare la socket, inviare messaggi e gestire i pacchetti.
*   **ClientMain.java**: Il punto di avvio. Usa uno `Scanner` per farti scrivere i messaggi da inviare.

### Pacchetto `server`
*   **Server.java**: Gestisce l'ascolto sulla porta 5000 e l'estrazione dell'IP del mittente.
*   **ServerMain.java**: Il punto di avvio del server. Mostra a video i messaggi ricevuti finché non arriva lo `stop`.

## Come avviarlo

1. Per prima cosa, avvia il **ServerMain**. Vedrai la scritta `In attesa di pacchetti...`.
2. Avvia il **ClientMain**.
3. Digita un messaggio nel terminale del Client e premi **Invio**.
4. Controlla il terminale del Server: vedrai il tuo messaggio e il tuo IP (es. `127.0.0.1`).
5. Digita `stop` per chiudere tutto.
