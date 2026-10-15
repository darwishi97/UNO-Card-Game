
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame extends Game {

    private UnoDeck deck;
    private ArrayList<UnoCard> discardPile;
    private UnoCard topCard;
    private Scanner input;

    public UnoGame() {
        super("UNO");
        deck = new UnoDeck();
        deck.shuffle();
        discardPile = new ArrayList<>();
        input = new Scanner(System.in);
    }

    @Override
    public void play() {
        System.out.println("Welcome to UNO!");

        System.out.print("Enter Player 1 name: ");
        UnoPlayer player1 = new UnoPlayer(input.nextLine());

        System.out.print("Enter Player 2 name: ");
        UnoPlayer player2 = new UnoPlayer(input.nextLine());

        getPlayers().add(player1);
        getPlayers().add(player2);

        for (Player player : getPlayers()) {
            UnoPlayer unoPlayer = (UnoPlayer) player;

            for (int i = 0; i < 7; i++) {
                unoPlayer.drawCard(drawFromDeck());
            }
        }

        topCard = drawFromDeck();
        discardPile.add(topCard);

        int currentPlayer = 0;

        while (true) {
            UnoPlayer player =
                    (UnoPlayer) getPlayers().get(currentPlayer);

            System.out.println("\nTop card: " + topCard);
            player.showHand();

            System.out.println("Enter a card number to play,");
            System.out.println("or enter 0 to draw a card:");
            int choice = input.nextInt();

            if (choice == 0) {
                player.drawCard(drawFromDeck());
                System.out.println("You drew a card.");
                currentPlayer = (currentPlayer + 1) % 2;
                continue;
            }

            ArrayList<UnoCard> hand = player.getHand();

            if (choice < 1 || choice > hand.size()) {
                System.out.println("Invalid card number.");
                continue;
            }

            UnoCard selectedCard = hand.get(choice - 1);

            if (!selectedCard.canPlayOn(topCard)) {
                System.out.println("You cannot play that card.");
                continue;
            }

            topCard = selectedCard;
            discardPile.add(selectedCard);
            hand.remove(choice - 1);

            System.out.println(player.getPlayerID()
                    + " played " + selectedCard);

            if (player.hasWon()) {
                declareWinner();
                break;
            }

            currentPlayer = (currentPlayer + 1) % 2;
        }
    }

    private UnoCard drawFromDeck() {
        if (deck.showCards().isEmpty()) {
            if (discardPile.size() <= 1) {
                throw new IllegalStateException(
                        "No cards left to draw.");
            }

            UnoCard currentTop = discardPile.remove(
                    discardPile.size() - 1);

            for (UnoCard card : discardPile) {
                deck.addCard(card);
            }

            discardPile.clear();
            discardPile.add(currentTop);
            topCard = currentTop;

            deck.shuffle();
        }

        return (UnoCard) deck.showCards().remove(0);
    }

    @Override
    public void declareWinner() {
        for (Player player : getPlayers()) {
            UnoPlayer unoPlayer = (UnoPlayer) player;

            if (unoPlayer.hasWon()) {
                System.out.println("\nCongratulations! "
                        + unoPlayer.getPlayerID() + " wins!");
                return;
            }
        }
    }

    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.play();
    }
}
