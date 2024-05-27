package org.events;

import org.events.exceptions.EventException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        Concert concert= new Concert("Concerto figo", LocalDate.parse("2024-12-20"), 500, LocalTime.parse("15:00"), 12.00 );
        System.out.println(concert);

        System.out.print("Inserisci il titolo dell'evento: ");
        String title= scanner.nextLine();

        LocalDate date= getDate(scanner);

        int seats= getSeats(scanner, " totali: ");

        Event event= null;
        String choice=null;


        try {
            event = new Event(title, date, seats);
            System.out.println(event);
            do {
                System.out.println("1-prenota 2-disdici 3-esci");
                choice= scanner.nextLine();

                switch (choice){

                    // Prenotazione
                    case "1":
                        System.out.println("Quanti posti vuoi prenotare? ");
                        int seatsToBook= getSeats(scanner, " da preonatare: ");
                        try {
                            event.book(seatsToBook);
                            System.out.println("Operazione effettuata con successo");
                            System.out.println(event);
                        } catch (EventException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    // Cancellazione
                    case "2":
                        System.out.println("Quante prenotazioni vuoi disdire? ");
                        int seatsToCancel= getSeats(scanner, " da cancellare: ");
                        try {
                            event.cancelBookings(seatsToCancel);
                            System.out.println("Operazione effettuata con successo");
                            System.out.println(event);
                        } catch (EventException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    // Chiusura menù
                    case "3":
                        break;

                    // Scelta non valida
                    default:
                        System.out.println("Scelta non valida");
                        break;
                }
            } while (!choice.equals("3"));
        } catch (EventException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Metodo per ricevere una data dall'utente
    private static LocalDate getDate(Scanner scanner){
        LocalDate date= null;
        while (date== null){
            System.out.print("Inserisci una data (yyyy-MM-dd): ");
            String dateString= scanner.nextLine();
            try{
                date= LocalDate.parse(dateString);
            }catch (DateTimeParseException e){
                System.out.println("Il formato della data non è valido " + e.getMessage());
            }
        }
        return date;
    }

    // Metodo per ricevere i posti dall'utente
    private static int getSeats(Scanner scanner, String messagePlus){
        Integer seats= null;
        while (seats== null){
            System.out.print("Inserisci il numero di posti " + messagePlus);
            String seatsString= scanner.nextLine();
            try {
                seats= Integer.parseInt(seatsString);
            }catch (NumberFormatException e){
                System.out.println("Devi inserire un numero.");
            }
        }
        return seats;
    }
}
