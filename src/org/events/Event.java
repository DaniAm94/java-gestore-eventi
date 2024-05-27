package org.events;

import org.events.exceptions.EventException;

import java.time.LocalDate;

public class Event {
    private String title;
    private LocalDate date;
    private int totalSeats;
    private int bookedSeats;

    public Event(String title, LocalDate date, int totalSeats) {
        this.title = validateTitle(title);
        this.date = validateDate(date);
        this.totalSeats = validateSeats(totalSeats);
        this.bookedSeats = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = validateTitle(title);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = validateDate(date);
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    // Metodo per prenotare
    public void book(int seats){
        if(seats <= 0){
            throw new EventException("Devi prenotare un numero di posti che sia maggiore di 0");
        } else if (seats > totalSeats - bookedSeats){
            throw new EventException("Prenotazione non valida. Ci sono " + (totalSeats - bookedSeats) + " posti disponibili");
        } else if(date.isBefore(LocalDate.now())){
            throw new EventException("Non è più possibile effettuare prenotazioni per l'evento selezionato");
        }
        bookedSeats += seats;
    }

    // Metodo per disdire delle prenotazioni
    public void cancelBookings(int seats){
        if(seats <= 0){
            throw new EventException("Puoi cancellare un numero di posti che sia maggiore di 0");
        } else if (seats > bookedSeats){
            throw new EventException("Cancellazione non valida. E' possibile cancellare fino a " +  bookedSeats + " posti");
        } else if(date.isBefore(LocalDate.now())){
            throw new EventException("Non è più possibile disdire delle prenotazioni per l'evento selezionato");
        }
        bookedSeats -= seats;
    }

    // Metodo per la validazione del titolo
    private String validateTitle(String title){
        if(title== null || title.isEmpty()){
            throw new EventException("E' necessario inserire un titolo");
        }
        return title;
    }

    // Metodo per la validazione della data
    private LocalDate validateDate(LocalDate date){
        if(date == null || date.isBefore(LocalDate.now())){
            throw new EventException("Data non valida:" + date);
        }
        return date;
    }

    // Metodo per la validazione dei posti a sedere
    private int validateSeats(int seats){
        if (seats <= 0 ){
            throw new EventException("Devi inserire un numero maggiore di 0");
        }
        return seats;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", totalSeats=" + totalSeats +
                ", bookedSeats=" + bookedSeats +
                '}';
    }
}
