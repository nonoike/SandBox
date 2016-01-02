package jp.co.trump;

import lombok.Value;

/**
 * トランプの一枚分に該当するクラス。
 *
 * @author user
 */
@Value
public class Card {
    /** スート */
    private final Suit suit;
    /** ランク */
    private final Rank rank;

    /**
     * コンストラクタ
     *
     * @param suit スート
     * @param rank ランク
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
}
