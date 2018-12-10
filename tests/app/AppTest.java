package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void main1() {
        App.main("resources/bibtexExample1.bib".split(" "));
    }

    @Test
    void main2() {
        App.main("resources/bibtexExample1.bib , Mihalis Yannakakis".split(" "));
    }

    @Test
    void main4() {
        App.main("resources/bibtexExample1.bib , Yannakakis".split(" "));
    }

    @Test
    void main3() {
        App.main("resources/bibtexExample1.bib , , MISC | ARTICLE".split(" "));
    }
}
