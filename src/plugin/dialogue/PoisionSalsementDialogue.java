package plugin.dialogue;

import org.arios.game.content.dialogue.DialoguePlugin;
import org.arios.game.content.dialogue.FacialExpression;
import org.arios.game.node.entity.npc.NPC;
import org.arios.game.node.entity.player.Player;

/**
 * Handles the PoisionSalsementDialogue dialogue.
 * @author 'Vexia
 */
public class PoisionSalsementDialogue extends DialoguePlugin {

	public PoisionSalsementDialogue() {

	}

	public PoisionSalsementDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 820 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.NORMAL, "I'm afraid I'm all sold out of poison at the moment.", "People know a bargain when they see it!");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Hello.");
				stage = 20;
				break;

			}
			break;
		case 10:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Howdy! Thanks for buying all that low alcohol", "beer from me! Now I have the funds to whip up a new", "batch of my patented multipurpose poision!");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Maybe I can take your name and add it to my mailing", "list for potential purchasers of Peter Potter's patented", "multi-purpose poison?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Thanks, but no thanks.");
			stage = 23;
			break;
		case 23:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PoisionSalsementDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Talk about the Murder Mystery Quest", "Talk about the Fremennik Trials");
		stage = 0;
		return true;
	}
}