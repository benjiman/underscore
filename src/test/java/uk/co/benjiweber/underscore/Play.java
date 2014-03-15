package uk.co.benjiweber.underscore;

public class Play {
    public final String author;
    public final String name;
    public final int year;

    Play(String author, String name, int year) {
        this.author = author;
        this.name = name;
        this.year = year;
    }

    public String author() { return author; }
    public int year() { return year; }

    public String toString() {
        return author + " - " + name + " - " + year;
    }
}
