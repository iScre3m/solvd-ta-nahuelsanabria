package homework2.publication;

import homework2.interfaces.IBuyable;

import java.util.Objects;

public class NewsPaper extends Publication implements IBuyable {
    private final String editorial;

    public NewsPaper(String title, String editorial, int pages, boolean available, Genre genre, String content) {
        super(title, pages, available, genre, content);
        this.editorial = editorial;
    }

    public String getEditorial() {
        return editorial;
    }

    @Override
    public String toString() {
        String available = (this.available) ? "Available" : "Not Available";
        return "NewsPaper '" + title + '\'' +
                ", editorial: '" + editorial + '\'' +
                ", pages: " + pages +
                ", " + available +
                ", genre: " + genre +
                ", id: " + id;
    }

    @Override
    public int hashCode() {
        return 3 + pages + Integer.parseInt(title) * Integer.parseInt(editorial);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof NewsPaper) {
            NewsPaper newsPaper = (NewsPaper) obj;
            if (Objects.equals(newsPaper.getTitle(), this.title) && Objects.equals(newsPaper.getEditorial(), this.editorial) && (newsPaper.getPages() == this.pages) && (newsPaper.getGenre() == this.genre)) {
                return true;
            }
        }
        return false;
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
        return 1.10 * this.pages * countLetters * amountOfCopies;
    }
}
