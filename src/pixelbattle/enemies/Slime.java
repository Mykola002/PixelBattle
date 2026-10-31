package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Target;

public class Slime extends Enemy {
    public Slime() {
        super("Слиз", 60, new Sprite("swampy_anim", 4, 4));

        addSkill(new Skill("Стрибок", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(5, 9));
            return user.getName() + " стрибає на " + target.getName() + ": " + dmg + " шкоди";
        }));
    }

    // TODO 6: пасивка «Регенерація»
    @Override
    protected void onTurnStart() {
        heal(6);
    }
}