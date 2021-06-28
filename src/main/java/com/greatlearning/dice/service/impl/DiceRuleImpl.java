package com.greatlearning.dice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.greatlearning.dice.constants.CustomMessages;
import com.greatlearning.dice.constants.PlayerStatus;
import com.greatlearning.dice.model.Player;
import com.greatlearning.dice.service.Dice;
import com.greatlearning.dice.service.DiceRule;
import com.greatlearning.dice.utils.PrintMessage;

public class DiceRuleImpl implements DiceRule {

	Dice dice;
	PrintMessage printMessage;

	static Scanner scanner = new Scanner(System.in);

	public DiceRuleImpl() {
		dice = new DiceImpl();
		printMessage = new PrintMessage();
	}

	protected void finalize() {
		scanner.close();
	}

	@Override
	public void displayRanks(List<Player> playerList) {
		List<Player> playerInGameList = new ArrayList<>();
		List<Player> playerWonList = new ArrayList<>();
		playerList.forEach(player -> {
			if (player.getStatus().equals(PlayerStatus.WON))
				playerWonList.add(player);
			else
				playerInGameList.add(player);
		});
		playerWonList.sort((player1, player2) -> player2.getRank() - player1.getRank());
		playerInGameList.sort((player1, player2) -> player2.getTotalPoint() - player1.getTotalPoint());
		for (int i = 0; i < playerWonList.size(); i++) {
			printMessage.print(CustomMessages.PLAYER_WON_DASHBOARD, (i + 1), playerWonList.get(i).getId());
		}
		for (int i = 0; i < playerInGameList.size(); i++) {
			printMessage.print(CustomMessages.INGAME_DASHBOARD, (i + playerWonList.size() + 1),
					playerInGameList.get(i).getId(), playerInGameList.get(i).getTotalPoint());
		}

	}

	@Override
	public List<Player> setUpGame(Integer noOfPlayer) {
		List<Player> playerList = IntStream.range(0, noOfPlayer).mapToObj(i -> {
			Player player = new Player();
			player.setId("Player-" + (i + 1));
			player.setStatus(PlayerStatus.CAN_PLAY);
			player.setTotalPoint(0);
			return player;
		}).collect(Collectors.toList());
		return playerList;
	}

	@Override
	public Integer playerWins(List<Player> playerList, Integer idx, Integer playerInGame) {
		printMessage.print(CustomMessages.PLAYER_WINS, playerList.get(idx).getId(),
				(playerList.size() - playerInGame + 1));
		playerInGame--;
		playerList.get(idx).setStatus(PlayerStatus.WON);
		playerList.get(idx).setRank(playerList.size() - playerInGame);
		displayRanks(playerList);
		return playerInGame;
	}

	@Override
	public void startGame(List<Player> playerList, Integer noOfPlayer, Integer points) {
		int diceNumber = 0, playersRemaining = noOfPlayer;
		while (playersRemaining > 1) {
			for (int i = 0; i < noOfPlayer; i++) {
				if (playersRemaining < 2) {
					break;
				}
				if (playerList.get(i).getStatus().equals(PlayerStatus.TWO_CONSECUTIVE_ONE)) {
					printMessage.print(CustomMessages.GOT_TWO_ONES, playerList.get(i).getId());
					playerList.get(i).setStatus(PlayerStatus.GOT_ONE_LAST_CHANCE);
					continue;
				} else if (playerList.get(i).getStatus().equals(PlayerStatus.WON)) {
					continue;
				}
				printMessage.print(CustomMessages.ROLL_DICE, playerList.get(i).getId());
				diceNumber = rollDiceAndUpdateLeaderBoard(playerList, i);
				if (playerList.get(i).getTotalPoint() >= points) {
					playersRemaining = playerWins(playerList, i, playersRemaining);
					continue;
				} else if (diceNumber == 6) {
					playerList.get(i).setStatus(PlayerStatus.CAN_PLAY);
					printMessage.print(CustomMessages.GOT_SIX, playerList.get(i).getId());
					diceNumber = rollDiceAndUpdateLeaderBoard(playerList, i);
					if (playerList.get(i).getTotalPoint() >= points) {
						playersRemaining = playerWins(playerList, i, playersRemaining);
						continue;
					}
				}
				if (diceNumber == 1) {
					playerList.get(i)
							.setStatus(playerList.get(i).getStatus().equals(PlayerStatus.GOT_ONE_LAST_CHANCE)
									? PlayerStatus.TWO_CONSECUTIVE_ONE
									: PlayerStatus.GOT_ONE_LAST_CHANCE);
				} else {
					playerList.get(i).setStatus(PlayerStatus.CAN_PLAY);
				}
				displayRanks(playerList);
			}
		}
		printMessage.print(CustomMessages.GAME_FINISHED);
	}

	private int rollDiceAndUpdateLeaderBoard(List<Player> playerList, int i) {
		int diceNumber;
		String str = "";
		do {
			if (!str.equalsIgnoreCase(""))
				printMessage.print(CustomMessages.ROLL_DICE_AGAIN);
			str = scanner.next();
		} while (!str.equalsIgnoreCase("r"));
		diceNumber = dice.rollDice();
		int prev = playerList.get(i).getTotalPoint();
		playerList.get(i).setTotalPoint(prev + diceNumber);
		return diceNumber;
	}

}
