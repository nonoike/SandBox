package jp.co.trump;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * デッキに該当するクラス。
 *
 * @author user
 */
public class Deck {
    /** デッキ内のカード */
    private List<Card> cards;

    /** コンストラクタ */
    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * デッキからカードを引きます。<br>
     * カードがない場合はnullを返します。
     *
     * @return 引いたカード
     */
    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    /**
     * デッキにカードを追加します。
     *
     * @param card 追加するカード
     */
    public void add(Card card) {
        cards.add(card);
    }

    /**
     * デッキ内のカードをシャッフルします。
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }
}
