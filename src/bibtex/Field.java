package bibtex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class Field {

    public final String name;
    public final String value;

    public Field(String name, String value) {
        this.name = name.trim();
        if (this.name.equals("author") || this.name.equals("editor")) {
            this.value = value.replace(" and ", " | ").trim();
        } else {
            this.value = value.trim();
        }
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

    public String[] toStrings() {
        LinkedList<String> strings = new LinkedList<>();
        strings.add(this.name);
        if (this.name.equals("author") || this.name.equals("editor")) {
            String[] arrayList = this.value.split(" \\| ");
            for (String s : arrayList) {
                strings.add(s);
            }
        } else {
            strings.add(this.value);
        }

        Object[] objects = strings.toArray();
        String[] result = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            result[i] = (String) objects[i];
        }
        return result;
    }

    public int hashCode() {
        return name.hashCode();
    }

}
