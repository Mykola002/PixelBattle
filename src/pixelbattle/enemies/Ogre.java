package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Target;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Ogre extends Enemy {
    public Ogre() {
        super("Огр", 200, new Sprite("ogre_idle_anim", 4, 4));

        addSkill(new Skill("Удар кийком", Target.ENEMY, 0, (user, target) -> {
            int dmg = random(14, 20);
            if (user.getHp() < user.getMaxHp() / 2) dmg += 8; // лють
            return user.getName() + " б'є кийком " + target.getName() + ": " + target.takeDamage(dmg) + " шкоди";
        }));

        // TODO 10: ультимейт «Землетрус» (анонімний клас)
        setUltimate(new Ultimate() {
            @Override
            public String getName() {
                return "Землетрус";
            }

            @Override
            public String getDescription() {
                return "Кожному живому герою 12 шкоди, огр лікується на 20";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                for (Fighter hero : enemies) {
                    if (hero.isAlive()) {
                        hero.takeDamage(12);
                    }
                }
                user.heal(20);
                return user.getName() + " влаштовує Землетрус!";
            }
        });
    }

    @Override
    protected int modifyIncomingDamage(int damage) {
        return Math.min(damage, 25);
    }
}