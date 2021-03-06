package plugin.dialogue;

import org.arios.game.content.dialogue.DialoguePlugin;
import org.arios.game.content.dialogue.FacialExpression;
import org.arios.game.node.entity.npc.NPC;
import org.arios.game.node.entity.player.Player;

/**
 * Represents the dialogue used for sir tiffy.
 * @author 'Vexia
 * @version 1.0
 */
public final class SirTiffyCashienDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code SirTiffyCashienDialogue} {@code Object}.
	 */
	public SirTiffyCashienDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SirTiffyCashienDialogue} {@code Object}.
	 * @param player the player.
	 */
	public SirTiffyCashienDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SirTiffyCashienDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NORMAL, "Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "What ho, sirrag.", "Spiffing day for a walk in the park, what?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Spiffing?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Absolutely, top-hole!", "Well, can't stay and chat all day, dontchaknow!", "Ta-ta for now!");
			stage = 10;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Erm...goodbye.");
			stage = 4;
			break;
		case 4:
			end();
			break;
		case 10:
			npc("Would you like to look at my wares?");
			stage++;
			break;
		case 11:
			player("Yes, please.");
			stage++;
			break;
		case 12:
			npc.openShop(player);
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2290 };
	}
}