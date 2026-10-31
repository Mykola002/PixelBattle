package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Meteor implements Ultimate {
    @Override
    public String getName() {
        return "Метеорит";
    }

    @Override
    public String getDescription() {
        return "Падіння метеорита на всіх ворогів (20-30 шкоди)";
    }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int totalDmg = 0;
        for (Fighter e : enemies) {
            if (e.isAlive()) {
                int dmg = e.takeDamage(Fighter.random(20, 30));
                totalDmg += dmg;
            }
        }
        return user.getName() + " кличе метеорит! Загальна шкода: " + totalDmg;
    }
}