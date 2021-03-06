package plugin.dialogue;

import org.arios.game.content.dialogue.DialoguePlugin;
import org.arios.game.content.dialogue.FacialExpression;
import org.arios.game.node.entity.npc.NPC;
import org.arios.game.node.entity.player.Player;

/**
 * Handles the SigmundDialogue dialogue.
 * @author 'Vexia
 */
public class SigmundDialogue extends DialoguePlugin {

	public SigmundDialogue() {

	}

	public SigmundDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 2082, 2083, 2090, 3713, 3716, 3717, 3718, 3719, 3720, 4328, 4331, 4332, 4333, 4334, 4335 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Do you have any quests for me?", "Who are you?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Do you have any quests for me?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Who are you?");
				stage = 20;
				break;

			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "I'm the Duke's advisor.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Can you give me any advice then?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "I only advise the Duke. But if you want to make", "yourself useful, there are evil goblins to slay on the", "other side of the river.");
			stage = 23;
			break;
		case 23:
			end();
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "I hear the Duke has a task for an adventurer.", "Otherwise, if you want to make yourself useful, there", "are always evil monsters to slay.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Okay, I might just do that.");
			stage = 12;
			break;
		case 12:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SigmundDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Can I help you?");
		stage = 0;
		return true;
	}
}