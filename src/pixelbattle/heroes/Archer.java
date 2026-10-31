package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Target;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Archer extends Hero {
    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(10, 16));
            return user.getName() + " стріляє в " + target.getName() + ": " + dmg + " шкоди";
        }));

        // TODO 3: Подвійний постріл
        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int dmg1 = target.takeDamage(random(7, 11));
            int dmg2 = target.takeDamage(random(7, 11));
            int sum = dmg1 + dmg2;
            return user.getName() + " робить подвійний постріл по " + target.getName() + ": " + sum + " шкоди";
        }));

        // TODO 9: Ультимейт «Злива стріл» (анонімний клас з полем-лічильником)
        setUltimate(new Ultimate() {
            private int n = 3;

            @Override
            public String getName() {
                return "Злива стріл";
            }

            @Override
            public String getDescription() {
                return "Випускає стріли по всіх ворогах (кількість зростає)";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int currentN = n;
                n++;
                for (Fighter e : enemies) {
                    if (e.isAlive()) {
                        for (int i = 0; i < currentN; i++) {
                            e.takeDamage(Fighter.random(4, 8));
                        }
                    }
                }
                return user.getName() + " влаштовує зливу стріл (" + currentN + " стріл на кожного)!";
            }
        });
    }

    // TODO 5: пасивка «Ухилення»
    @Override
    protected int modifyIncomingDamage(int damage) {
        if (chance(25)) {
            return 0;
        }
        return damage;
    }
}
