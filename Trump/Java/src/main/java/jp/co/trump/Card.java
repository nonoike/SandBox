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
}
