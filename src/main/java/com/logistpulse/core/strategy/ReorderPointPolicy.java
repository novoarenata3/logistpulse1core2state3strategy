package com.logistpulse.core.strategy;

/** Si el stock cae al punto de reorden, repone hasta el nivel maximo. */
public class ReorderPointPolicy implements ReplenishmentPolicy {
    @Override public String name() { return "Punto de reorden"; }
    @Override public int orderQuantity(int available, ProductParams p) {
        return available <= p.reorderPoint() ? Math.max(0, p.maxLevel() - available) : 0;
    }
}
