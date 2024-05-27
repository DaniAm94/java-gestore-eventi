package org.events;

import org.events.exceptions.EventException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Concert extends Event{

    private LocalTime time;
    private BigDecimal price;

    public Concert(String title, LocalDate date, int totalSeats, LocalTime time, double price) {
        super(title, date, totalSeats);
        this.time= time;
        this.price= validatePrice(price);
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal validatePrice(double price) {
        if (price < 0){
            throw new EventException("Il prezzo deve essere un numero positivo");
        }
        return new BigDecimal(price);
    }

    // Metodo che restituisce il prezzo formattato
    private String formattedPrice(){
        return price.setScale(2, RoundingMode.HALF_UP) + "€";
    }

    // Metodo che restituisce data e ora formattate
    private String formattedTime(){
        DateTimeFormatter formatter= DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT);
        return String.format("%s", LocalDateTime.of(getDate(), getTime()).format(formatter));
    }

    @Override
    public String toString() {
        return "Concerto{ " +
                "titolo= '" + getTitle() + '\'' +
                ", data e ora= " + formattedTime() +
                ", prezzo biglietto= " + formattedPrice() +
                ", posti totali= " + getTotalSeats() +
                ", posti prenotati= " + getBookedSeats() +
                " }";
    }
}
