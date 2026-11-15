package com.logistpulse.core.strategy;

/** Cantidad economica de pedido: EOQ = sqrt(2 D S / H); se pide al llegar al punto de reorden. */
public class EoqPolicy implements ReplenishmentPolicy {
    @Override public String name() { return "EOQ"; }
    @Override public int orderQuantity(int available, ProductParams p) {
        if (available > p.reorderPoint()) return 0;
        return (int) Math.round(Math.sqrt(2 * p.annualDemand() * p.orderCost() / p.holdingCost()));
    }
}
