package org.events;

import org.events.exceptions.EventException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        System.out.print("Inserisci il titolo dell'evento: ");
        String title= scanner.nextLine();

        LocalDate date= getDate(scanner);

        int seats= getSeats(scanner);

        Event event= null;
        try {
            event = new Event(title, date, seats);
            System.out.println(event);
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
    private static int getSeats(Scanner scanner){
        Integer seats= null;
        while (seats== null){
            System.out.print("Inserisci il numero di posti totali: ");
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
