package nl.gui.kalahaweb.model.domain;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BOARD_PIT_SIZE = 14;
	private int[] pitList;
	private Player playerNorth;
	private Player playerSouth;

	public Board() {
		initBoard();
	}

	private void initBoard() {
		this.pitList = new int[BOARD_PIT_SIZE];
		for (int i = 0; i < this.pitList.length; i++) {
			if (i == 6 || i == 13) {
				this.pitList[i] = 0;
				continue;
			}
			this.pitList[i] = 6;
		}
		int[] playerNorthPits = { 7, 8, 9, 10, 11, 12 };
		this.playerNorth = new Player(1, playerNorthPits);
		int[] playerSouthPits = { 0, 1, 2, 3, 4, 5 };
		this.playerSouth = new Player(2, playerSouthPits);
	}

	public int[] getPitList() {
		return pitList;
	}

	public Player getPlayerNorth() {
		return playerNorth;
	}

	public Player getPlayerSouth() {
		return playerSouth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pitList);
		result = prime * result + ((playerNorth == null) ? 0 : playerNorth.hashCode());
		result = prime * result + ((playerSouth == null) ? 0 : playerSouth.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.equals(pitList, other.pitList))
			return false;
		if (playerNorth == null) {
			if (other.playerNorth != null)
				return false;
		} else if (!playerNorth.equals(other.playerNorth))
			return false;
		if (playerSouth == null) {
			if (other.playerSouth != null)
				return false;
		} else if (!playerSouth.equals(other.playerSouth))
			return false;
		return true;
	}

}
