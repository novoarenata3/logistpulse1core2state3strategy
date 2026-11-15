package com.logistpulse.core.strategy;

/** Si el stock cae al minimo, repone hasta el maximo. */
public class MinMaxPolicy implements ReplenishmentPolicy {
    @Override public String name() { return "Min-Max"; }
    @Override public int orderQuantity(int available, ProductParams p) {
        return available <= p.minLevel() ? p.maxLevel() - available : 0;
    }
}
