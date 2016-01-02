package jp.co.trump.blackjack;

import static jp.co.trump.Rank.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.trump.Card;
import lombok.Getter;

/**
 * プレイヤーに該当するクラス。
 *
 * @author user
 */
public class Player {
    /** 所持カードの一覧 */
    @Getter
    private List<Card> cards;
    /** バストフラグ */
    @Getter
    private boolean isBust;

    /** コンストラクタ */
    public Player() {
        cards = new ArrayList<>();
        isBust = false;
    }

    /**
     * カードを追加します。
     *
     * @param card 追加するカード
     */
    public void addCard(Card card) {
        cards.add(card);
        calcScore();
    }

    /**
     * 所持カードのランクの一覧を文字列で返します。
     * 
     * @param isFullOpen trueならば全カードを表示、falseならば最初の一枚のみ表示
     * @return カードのランクの文字列
     */
    public String joinRanks(boolean isFullOpen) {
        if (isFullOpen) {
            return cards.stream().map(c -> c.getRank().toString()).collect(Collectors.joining(", "));
        }
        StringBuilder disp = new StringBuilder(cards.get(0).getRank().toString());
        for (int i = 1; i < cards.size(); i++) {
            disp.append(", ???");
        }
        return disp.toString();
    }

    /**
     * 点数を計算します。
     * 
     * @return 点数
     */
    public int calcScore() {
        int total = 0;
        for (Card card : cards) {
            if (ACE.equals(card.getRank())) {
                total += 11;
                continue;
            }
            if (10 < card.getRank().getValue()) {
                total += 10;
                continue;
            }
            total += card.getRank().getValue();
        }
        long aceCount = cards.stream().filter(c -> ACE.equals(c.getRank())).count();
        // ACEを11ではなく1として扱う
        while (21 < total && 0 < aceCount) {
            total -= 10;
            aceCount--;
        }

        if (21 < total) {
            isBust = true;
        }
        return total;
    }
}
