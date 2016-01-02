package jp.co.trump.blackjack;

/**
 * プレイヤーの行動内容の定義クラス。
 *
 * @author user
 */
public enum Action {
    /** 該当なし */
    NONE,
    /** ヒット */
    HIT,
    /** スタンド */
    STAND,;

    /**
     * 該当する{@link Action}を返します。
     *
     * @param name 行動内容の文字列
     * @return 該当する行動内容もしくはNONE
     */
    public static Action quietValueOf(String name) {
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
