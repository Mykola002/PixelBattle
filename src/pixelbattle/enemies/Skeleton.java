package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Target;

public class Skeleton extends Enemy {
    private boolean canResurrect = true; // Поле для пасивки «Воскресіння»

    public Skeleton() {
        super("Скелет", 50, new Sprite("skelet_idle_anim", 4, 6));

        // Атака «Кістяний удар» (8–12 шкоди)
        addSkill(new Skill("Кістяний удар", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(8, 12));
            return user.getName() + " б'є кістками " + target.getName() + ": " + dmg + " шкоди";
        }));
    }

    // Пасивка «Воскресіння»: перший фатальний удар залишає 1 HP
    @Override
    public int takeDamage(int damage) {
        int actualDamage = super.takeDamage(damage);

        // Якщо здоров'я впало до 0 або нижче, але воскресіння ще доступне
        if (!isAlive() && canResurrect) {
            canResurrect = false;
            heal(1); // Залишаємо 1 HP
        }

        return actualDamage;
    }
}