
package ca.sheridancollege.project;

import java.util.ArrayList;

public class UnoPlayer extends Player {

    private ArrayList<UnoCard> hand;

    public UnoPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public void drawCard(UnoCard card) {
        hand.add(card);
    }

    public ArrayList<UnoCard> getHand() {
        return hand;
    }

    public boolean hasWon() {
        return hand.isEmpty();
    }

    @Override
    public void play() {
        // Game logic will be handled by UnoGame.
    }

    public void showHand() {
        System.out.println(getPlayerID() + "'s hand:");

        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }
}