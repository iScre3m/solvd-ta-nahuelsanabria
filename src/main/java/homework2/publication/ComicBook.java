package homework2.publication;

import homework2.interfaces.IBuyable;

public class ComicBook extends Publication implements IBuyable {

    private final String editorial;

    public ComicBook(String title, String editorial, int pages, boolean available, Genre genre, String content) {
        super(title, pages, available, genre, content);
        this.editorial = editorial;
    }

    public String getEditorial() {
        return editorial;
    }


    @Override
    public double calculateBuyPrice(int amountOfCopies) {
        int countLetters = 0;
        for (int i = 0; i < this.content.length(); i++) {
            countLetters++;
        }
        for (int i = 0; i < this.title.length(); i++) {
            countLetters++;
        }
        this.setAvailable(false);
        return 1.35 * this.pages * countLetters * amountOfCopies;
    }
}
