import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    private void rollsMany(int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    public void すべて0本だったら最終スコアは0() {
        rollsMany(20, 0);
        assertThat(game.getScore()).isEqualTo(0);
    }

    @Test
    public void ピンを毎回1本倒したらゲームのスコアは20() {
        rollsMany(20, 1);
        assertThat(game.getScore()).isEqualTo(20);
    }

    @Test
    public void スペアを1回だすと次の投球がボーナスとして加算される() {
        rollSpare();
        game.roll(3);
        rollsMany(17, 0);
        assertThat(game.getScore()).isEqualTo(16);
    }

    @Test
    public void ストライクを1回出すと次の投球と更に次の投球がボーナスとして加算される() {
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollsMany(16, 0);
        assertThat(game.getScore()).isEqualTo(24);
    }

    @Test
    public void すべてストライクを出すとスコアが300になる() {
        rollsMany(12, 10);
        assertThat(game.getScore()).isEqualTo(300);
    }
}