package bibtex;

public class Field {

    public final String name;
    public final String value;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public boolean equals(Field field) {
        return this.name.equals(field.name);
    }

}
