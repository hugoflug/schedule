package se.kth.hugosa.schedule;

public class Classroom {
    public String name;
    public int capacity;

    public Classroom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object object) {
        return name.equals(((Classroom)object).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
