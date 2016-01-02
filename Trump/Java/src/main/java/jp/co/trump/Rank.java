package jp.co.trump;

import lombok.Getter;

/**
 * ランクの定義クラス。
 *
 * @author user
 */
public enum Rank {
    /** エース（1） */
    ACE(1),
    /** 2 */
    TWO(2),
    /** 3 */
    THREE(3),
    /** 4 */
    FOUR(4),
    /** 5 */
    FIVE(5),
    /** 6 */
    SIX(6),
    /** 7 */
    SEVEN(7),
    /** 8 */
    EIGHT(8),
    /** 9 */
    NINE(9),
    /** 10 */
    TEN(10),
    /** ジャック（11） */
    JACK(11),
    /** クイーン（12） */
    QUEEN(12),
    /** キング（13） */
    KING(13),;

    /** 通常のランクの値 */
    @Getter
    private final int value;

    /**
     * コンストラクタ
     *
     * @param value ランクの値
     */
    private Rank(final int value) {
        this.value = value;
    }
}
