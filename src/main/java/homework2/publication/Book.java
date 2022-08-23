package homework2.publication;


import homework2.interfaces.IBuyable;
import homework2.interfaces.IDiscount;

import java.util.Objects;

public class Book extends Publication implements IBuyable, IDiscount {

    private final String author;

    public Book(String title, String author, int pages, boolean available, Genre genre, String content) {
        super(title, pages, available, genre, content);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        String available = (this.available) ? "Available" : "Not Available";
        return "Book '" + title + '\'' +
                ", author: '" + author + '\'' +
                ", pages: " + pages +
                ", " + available +
                ", genre: " + genre +
                ", id: " + id;
    }

    @Override
    public int hashCode() {
        return 7 + pages + Integer.parseInt(title) * Integer.parseInt(author);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Book) {
            Book book = (Book) obj;
            if (Objects.equals(book.getTitle(), this.title) && Objects.equals(book.getAuthor(), this.author) && (book.getPages() == this.pages) && (book.getGenre() == this.genre)) {
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
        return 2.35 * this.pages * countLetters * amountOfCopies;
    }


    @Override
    public double addDiscount() {
        return discountValue;
    }
}
