package nl.gui.kalahaweb.model.domain;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

class KalahaTest {

	@Test
	void testEmptySelectedPit() {
		// Test empty selected pit first play
		Kalaha.newGame();
		int selectedPit = 3;
		Kalaha.play(selectedPit);
		assertEquals(0, Kalaha.board.getPitList()[selectedPit]);
	}

	@Test
	void testPlayerTurnChanged() {
		// Test empty selected pit first play
		Kalaha.newGame();
		int selectedPit = 3;
		Kalaha.play(selectedPit);
		assertEquals(2, Kalaha.playerTurn);
	}

	@Test
	void testPlayerSelectedInvalidPit() {
		Kalaha.newGame();
		int selectedPit = 7;
		assertThrows(IllegalArgumentException.class, () -> {
			Kalaha.play(selectedPit);
		});
	}

	@Test
	void testStealStones() {
		int selectedPit = 0;
		Kalaha.newGame();
		Kalaha.board.getPitList()[0] = 1;
		Kalaha.board.getPitList()[1] = 0;
		Kalaha.play(selectedPit);
		assertEquals(0, Kalaha.board.getPitList()[1]);
		assertEquals(0, Kalaha.board.getPitList()[11]);
	}

	@Test
	void testJumpPlayer2KalahaOnPlayer1Play() {
		Kalaha.newGame();

		// set selected pit 5
		int selectedPit = 5;

		// set value to player 2's Kalaha
		Kalaha.board.getPitList()[13] = 27;

		// set value to selected pit that goes beyond the player 2's Kalaha
		Kalaha.board.getPitList()[selectedPit] = 12;

		Kalaha.play(selectedPit);

		assertEquals(27, Kalaha.board.getPitList()[13]);
	}

	@Test
	void testJumpPlayer1KalahaOnPlayer2Play() {
		Kalaha.newGame();
		Kalaha.playerTurn = 2;

		// set selected pit 12
		int selectedPit = 12;

		// set value to player 1's Kalaha
		Kalaha.board.getPitList()[6] = 27;

		// set value to selected pit that goes beyond the player 1's Kalaha
		Kalaha.board.getPitList()[selectedPit] = 12;

		Kalaha.play(selectedPit);

		assertEquals(27, Kalaha.board.getPitList()[6]);
	}

	@Test
	void testPlayAgain() {
		// Test play again Player 1
		Kalaha.newGame();
		int selectedPit = 3;
		Kalaha.board.getPitList()[selectedPit] = 3;
		Kalaha.play(selectedPit);
		assertEquals(1, Kalaha.playerTurn);

		// Test play again Player 2
		Kalaha.newGame();
		Kalaha.playerTurn = 2;
		selectedPit = 10;
		Kalaha.board.getPitList()[selectedPit] = 3;
		Kalaha.play(selectedPit);
		assertEquals(2, Kalaha.playerTurn);
	}

	@Test
	void testGame1() {
		// Test first play if pit selected is number 5
		Kalaha.newGame();
		int selectedPit = 5;
		Kalaha.play(selectedPit);
		int sumNextPits = 0;
		for (int i = 6; i < 12; i++) {
			sumNextPits += Kalaha.board.getPitList()[i];
		}
		assertEquals(36, sumNextPits);
	}

	@Test
	void testGameOver() {
		Kalaha.newGame();
		int selectedPit = 0;
		// empty all pits
		for (int i = 0; i < 14; i++) {
			if (i == 6 || i == 13) {
				continue;
			}
			Kalaha.board.getPitList()[i] = 0;
		}
		Kalaha.play(selectedPit);
		assertEquals(true, Kalaha.gameOver);
	}

}
