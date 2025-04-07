package Characters.Abilities;

import Characters.Players.Player;

public class BasicAttack extends Ability {
    public BasicAttack() {
        super(AbilityType.BASIC_ATTACK, "Basic Attack", "",
                "🛡️", 0, 1, 0);

    }

    public void additionalFeatures(Player player) {
        player.updatePower(10);
    }
}
