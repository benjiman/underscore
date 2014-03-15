package uk.co.benjiweber.underscore;

public class Stooge {
    public final String name;
    public final int age;

    Stooge(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public String toString() {
        return name + " - " + age;
    }
}
