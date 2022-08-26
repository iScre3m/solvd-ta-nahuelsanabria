package homework2.interfaces;

import homework2.publication.PriceCopyColor;

public interface ICopyable {

    double pricePerPageCopy = 2.15;

    double calculateCopyPrice(int amountOfCopies, PriceCopyColor priceCopyColor);
}
