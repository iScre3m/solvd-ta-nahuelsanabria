package homework2.publication;

import homework2.interfaces.ICopyable;
import homework2.interfaces.IDiscount;

public class Pamphlet extends Publication implements ICopyable, IDiscount {
    public Pamphlet(String title, int pages, boolean available, Genre genre, String content) {
        super(title, pages, available, genre, content);
    }


    @Override
    public double calculateCopyPrice(int amountOfCopies) {
        int countLetters = 0;
        for (int i = 0; i < this.content.length(); i++) {
            countLetters++;
        }
        return pricePerPageCopy * this.pages * countLetters * 1.05;
    }

    @Override
    public double addDiscount() {
        return discountValue * 1.15;
    }
}
