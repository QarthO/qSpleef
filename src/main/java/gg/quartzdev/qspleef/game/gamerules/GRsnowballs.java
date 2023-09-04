package gg.quartzdev.qspleef.game.gamerules;

import gg.quartzdev.qspleef.qSpleef;

public class GRsnowballs extends SpleefGameRule {

    public GRsnowballs(qSpleef plugin) {
        super(plugin);
        this.name = this.getClass().getName().replaceFirst("GR", "");
    }

    @Override
    public void logic() {

    }
}
