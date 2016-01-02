package jp.co.trump.blackjack;

import static jp.co.trump.blackjack.Action.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import jp.co.trump.Deck;

/**
 * ブラックジャックを行うクラス。
 *
 * @author user
 */
public class BlackJack {
    /**
     * black jackのエントリポイント。
     *
     * @param args 初期パラメータ
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Player player = new Player();
        Player dealer = new Player();
        Deck deck = new Deck();
        deck.shuffle();
        player.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());

        // player
        Action action = NONE;
        while (HIT.equals(action) || NONE.equals(action)) {
            System.out.println("dealer:card[" + dealer.joinRanks(false) + "]");
            System.out.println(
                    "player:card[" + player.joinRanks(true) + "], score[" + player.calcScore() + "]");
            System.out.print("HIT or STAND:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            action = Action.quietValueOf(br.readLine().toUpperCase());
            if (NONE.equals(action)) {
                System.out.println("invalid input value.");
                continue;
            }
            if (HIT.equals(action)) {
                player.addCard(deck.draw());
            }
            if (player.isBust()) {
                break;
            }
        }

        // dealer
        while (dealer.calcScore() < 17 && !dealer.isBust() && !player.isBust()) {
            dealer.addCard(deck.draw());
        }

        // result
        int dealerScore = dealer.calcScore();
        int playerScore = player.calcScore();
        if (player.isBust()) {
            System.out.println("dealer win! player is bust!");
        } else if (dealer.isBust()) {
            System.out.println("player win! dealer is bust!");
        } else if (dealerScore < playerScore) {
            System.out.println("player win!");
        } else if (dealerScore == playerScore) {
            System.out.println("draw!");
        } else {
            System.out.println("dealer win!");
        }
        System.out.println("dealer:card[" + dealer.joinRanks(true) + "], score[" + dealerScore + "]");
        System.out.println("player:card[" + player.joinRanks(true) + "], score[" + playerScore + "]");
    }
}
