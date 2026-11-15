package com.logistpulse.core.observer;

import com.logistpulse.core.strategy.PolicyRegistry;
import com.logistpulse.core.strategy.ProductParams;
import com.logistpulse.core.strategy.ReplenishmentPolicy;

/** Suscriptor 2: usa la ReplenishmentPolicy (Strategy) del SKU para sugerir pedidos. */
public class ReplenishmentListener implements StockListener {
    private final PolicyRegistry policies;
    public ReplenishmentListener(PolicyRegistry policies) { this.policies = policies; }

    @Override public void onStockChanged(StockChanged e) {
        ProductParams p = policies.paramsFor(e.sku());
        if (p == null) return;
        ReplenishmentPolicy policy = policies.replenishmentFor(e.sku());
        int qty = policy.orderQuantity(e.currentAvailable(), p);
        if (qty > 0)
            System.out.println("   [REPOSICION] " + e.sku() + ": pedir " + qty + " u. (politica " + policy.name() + ")");
    }
}
