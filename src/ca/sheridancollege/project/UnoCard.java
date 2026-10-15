
package ca.sheridancollege.project;

public class UnoCard extends Card {

    private final String color;
    private final String value;

    public UnoCard(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public boolean canPlayOn(UnoCard topCard) {

        // If there is no card on the discard pile,
        // any card can be played.
        if (topCard == null) {
            return true;
        }

        // Wild cards can be played on any card.
        // Any card can be played on a Wild card.
        // Otherwise, match the color or value.
        return color.equals("Wild")
                || topCard.getColor().equals("Wild")
                || color.equals(topCard.getColor())
                || value.equals(topCard.getValue());
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}