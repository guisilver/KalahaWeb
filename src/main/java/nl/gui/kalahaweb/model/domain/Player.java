package nl.gui.kalahaweb.model.domain;

import java.io.Serializable;
import java.util.Arrays;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int playerNumber;
	private int[] playerAllowedPits;

	public Player(int playerNumber, int[] playerAllowedPits) {
		this.playerNumber = playerNumber;
		this.playerAllowedPits = playerAllowedPits;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public int[] getPlayerAllowedPits() {
		return playerAllowedPits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(playerAllowedPits);
		result = prime * result + playerNumber;
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
		Player other = (Player) obj;
		if (!Arrays.equals(playerAllowedPits, other.playerAllowedPits))
			return false;
		if (playerNumber != other.playerNumber)
			return false;
		return true;
	}

}
