
package ca.sheridancollege.project;

public class UnoDeck extends GroupOfCards {

    public UnoDeck() {
        super(108);
        createDeck();
    }

    private void createDeck() {
        String[] colors = {"Red", "Blue", "Green", "Yellow"};

        for (String color : colors) {
            addCard(new UnoCard(color, "0"));

            for (int i = 1; i <= 9; i++) {
                addCard(new UnoCard(color, String.valueOf(i)));
                addCard(new UnoCard(color, String.valueOf(i)));
            }

            String[] actionCards = {
                "Skip", "Reverse", "Draw Two"
            };

            for (String action : actionCards) {
                addCard(new UnoCard(color, action));
                addCard(new UnoCard(color, action));
            }
        }

        for (int i = 0; i < 4; i++) {
            addCard(new UnoCard("Wild", "Wild"));
            addCard(new UnoCard("Wild", "Wild Draw Four"));
        }
    }
}
