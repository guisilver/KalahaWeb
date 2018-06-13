package nl.gui.kalahaweb.model.domain;

import java.util.Arrays;

/**
 * This class is an implementation of the Kalaha Game
 * 
 * @author Guilherme Silveira
 *
 */
public class Kalaha {

	public static Board board;
	public static int playerTurn;
	public static int winner;
	public static boolean gameOver;

	static {
		newGame();
	}

	/**
	 * Starts a new game reseting the values.
	 */
	public static void newGame() {
		board = new Board();
		playerTurn = 1;
		gameOver = false;
	}

	/**
	 * Executes a play in the game, given a pit number.
	 * 
	 * @param selectedPit
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static int play(int selectedPit) throws IllegalArgumentException {
		if (!gameOver) {

			if (!checkValidPlayerPit(selectedPit)) {
				throw new IllegalArgumentException("This play is invalid! It's player's " + playerTurn + " turn.");
			}

			int lastPlayedPit = selectedPit;

			lastPlayedPit = sowStones(lastPlayedPit);

			gameOver = checkGameOver();

			if (gameOver) {
				endGame();
			}

			checkChangePlayerTurn(lastPlayedPit);
		}
		return playerTurn;
	}

	/**
	 * Sow the stones in the pits
	 * 
	 * @param lastPlayedPit
	 * @return
	 */
	private static int sowStones(int lastPlayedPit) {
		int stonesAmount = board.getPitList()[lastPlayedPit];
		board.getPitList()[lastPlayedPit] = 0;
		for (int i = 0; i < stonesAmount; i++) {
			lastPlayedPit++;
			if (playerTurn == 1 && lastPlayedPit == 13) {
				lastPlayedPit = 0;
			}
			if (playerTurn == 2 && lastPlayedPit == 6) {
				lastPlayedPit = 7;
			}
			if (lastPlayedPit > 13) {
				lastPlayedPit = 0;
				board.getPitList()[lastPlayedPit] += 1;
			} else {
				board.getPitList()[lastPlayedPit] += 1;
			}
		}
		checkStealStones(lastPlayedPit);
		return lastPlayedPit;
	}

	/**
	 * Check if it's possible to steal the stones from opposite pit
	 * 
	 * @param lastPlayedPit
	 */
	private static void checkStealStones(int lastPlayedPit) {
		if (lastPlayedPit != 13 && lastPlayedPit != 6) {
			int oppositeSidePit = 12 - lastPlayedPit;
			if (board.getPitList()[oppositeSidePit] > 0) {
				if (board.getPitList()[lastPlayedPit] == 1 && checkValidPlayerPit(lastPlayedPit)) {
					if (playerTurn == 1) {
						board.getPitList()[6] += board.getPitList()[lastPlayedPit];
						board.getPitList()[lastPlayedPit] = 0;
						board.getPitList()[6] += board.getPitList()[oppositeSidePit];
						board.getPitList()[oppositeSidePit] = 0;
					} else if (playerTurn == 2) {
						board.getPitList()[13] += board.getPitList()[lastPlayedPit];
						board.getPitList()[lastPlayedPit] = 0;
						board.getPitList()[13] += board.getPitList()[oppositeSidePit];
						board.getPitList()[oppositeSidePit] = 0;
					}
				}
			}
		}
	}

	/**
	 * Check if the player is allowed to play again on the next turn
	 * 
	 * @param lastPlayedPit
	 */
	private static void checkChangePlayerTurn(int lastPlayedPit) {
		if (lastPlayedPit == 6 && playerTurn == 1) {
			playerTurn = 1;
		} else if (lastPlayedPit == 13 && playerTurn == 2) {
			playerTurn = 2;
		} else {
			if (playerTurn == 1) {
				playerTurn = 2;
			} else {
				playerTurn = 1;
			}
		}
	}

	/**
	 * Check if the selected pit belongs to the current player's pits
	 * 
	 * @param selectedPit
	 * @return
	 */
	private static boolean checkValidPlayerPit(Integer selectedPit) {
		if (playerTurn == 1
				&& Arrays.stream(board.getPlayerSouth().getPlayerAllowedPits()).anyMatch(selectedPit::equals)) {
			return true;
		} else if (playerTurn == 2
				&& Arrays.stream(board.getPlayerNorth().getPlayerAllowedPits()).anyMatch(selectedPit::equals)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the players pits are empty
	 * 
	 * @return
	 */
	private static boolean checkGameOver() {
		int totalPlayer1 = 0;
		int totalPlayer2 = 0;

		for (int i = 0; i < 6; i++) {
			totalPlayer1 += board.getPitList()[i];
		}
		if (totalPlayer1 == 0) {
			return true;
		}

		for (int i = 7; i < 13; i++) {
			totalPlayer2 += board.getPitList()[i];
		}
		if (totalPlayer2 == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Ends the game
	 */
	private static void endGame() {
		for (int i = 0; i < 6; i++) {
			board.getPitList()[6] += board.getPitList()[i];
			board.getPitList()[i] = 0;
		}
		for (int i = 7; i < 13; i++) {
			board.getPitList()[13] += board.getPitList()[i];
			board.getPitList()[i] = 0;
		}
		if (board.getPitList()[6] > board.getPitList()[13]) {
			winner = 1;
		} else if (board.getPitList()[6] < board.getPitList()[13]) {
			winner = 2;
		} else {
			winner = 3;
		}
	}

	/**
	 * Returns a string in JSON format
	 * 
	 * @return
	 */
	public static String getKalahaJson() {
		String json = "{\"playerTurn\":" + Kalaha.playerTurn + ",\"board\": "
				+ Arrays.toString(Kalaha.board.getPitList()) + ", \"gameOver\": \"" + Kalaha.gameOver
				+ "\", \"winner\": \"" + Kalaha.winner + "\" }";
		return json;
	}

}
