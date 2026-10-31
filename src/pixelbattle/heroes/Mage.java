package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Target;
import pixelbattle.ultimates.Meteor;

public class Mage extends Hero {
    public Mage() {
        super("Маг", 70, new Sprite("wizzard_m_idle_anim", 4, 4));

        addSkill(new Skill("Удар посохом", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(6, 10));
            return user.getName() + " б'є посохом " + target.getName() + ": " + dmg + " шкоди";
        }));

        // TODO 2: Вогняна куля та Зцілення
        addSkill(new Skill("Вогняна куля", Target.ENEMY, 1, (user, target) -> {
            int dmg = target.takeDamage(random(22, 30));
            return user.getName() + " кидає вогняну кулю в " + target.getName() + ": " + dmg + " шкоди";
        }));

        addSkill(new Skill("Зцілення", Target.ALLY, 2, (user, target) -> {
            int healed = target.heal(25);
            return user.getName() + " зцілює " + target.getName() + " на " + healed + " HP";
        }));

        // TODO 8: підключення ультимейту «Метеорит»
        setUltimate(new Meteor());
    }
}