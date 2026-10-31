package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Target;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Knight extends Hero {
    public Knight() {
        super("Лицар", 110, new Sprite("knight_m_idle_anim", 4, 4));

        addSkill(new Skill("Удар мечем", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(12, 18));
            return user.getName() + " рубає " + target.getName() + ": " + dmg + " шкоди";
        }));

        // TODO 1: вміння «Щит» (Target.SELF, перезарядка 2)
        addSkill(new Skill("Щит", Target.SELF, 2, (user, target) -> {
            user.setGuarding(true);
            return user.getName() + " стає в захист";
        }));

        // TODO 7: ультимейт «Небесний молот» (анонімний клас)
        setUltimate(new Ultimate() {
            @Override
            public String getName() {
                return "Небесний молот";
            }

            @Override
            public String getDescription() {
                return "Кожному живому ворогу 15 шкоди та оглушення на 1 хід";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                for (Fighter e : enemies) {
                    if (e.isAlive()) {
                        e.takeDamage(15);
                        e.stun(1);
                    }
                }
                return user.getName() + " кличе Небесний молот!";
            }
        });
    }

    // TODO 4: пасивка «Важка броня»
    @Override
    protected int modifyIncomingDamage(int damage) {
        int reduced = damage - 4;
        return reduced < 0 ? 0 : reduced;
    }
}