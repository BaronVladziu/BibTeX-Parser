package bibtex;

public final class Field {

    public final String name;
    public final String value;

    public Field(String name, String value) {
        this.name = name.trim();
        this.value = value.trim();
    }

    public boolean equals(Object field) {
        return this.hashCode() == field.hashCode();
    }

    public boolean equals(Field field) {
        return this.name.equals(field.name) && this.value.equals(field.value);
    }

    public String toString() {
        return name + " = " + value;
    }

    public int hashCode() {
        return name.hashCode();
    }

}
