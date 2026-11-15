package com.logistpulse.core.strategy;

import java.util.HashMap;
import java.util.Map;

/** Contexto de Strategy: elige la politica por producto (con valores por defecto). */
public class PolicyRegistry {
    private final PickingPolicy defaultPicking;
    private final ReplenishmentPolicy defaultReplenishment;
    private final Map<String, PickingPolicy> picking = new HashMap<>();
    private final Map<String, ReplenishmentPolicy> replenishment = new HashMap<>();
    private final Map<String, ProductParams> params = new HashMap<>();

    public PolicyRegistry(PickingPolicy defaultPicking, ReplenishmentPolicy defaultReplenishment) {
        this.defaultPicking = defaultPicking; this.defaultReplenishment = defaultReplenishment;
    }

    public void configure(String sku, PickingPolicy pick, ReplenishmentPolicy rep, ProductParams p) {
        picking.put(sku, pick); replenishment.put(sku, rep); params.put(sku, p);
    }

    public PickingPolicy pickingFor(String sku) { return picking.getOrDefault(sku, defaultPicking); }
    public ReplenishmentPolicy replenishmentFor(String sku) { return replenishment.getOrDefault(sku, defaultReplenishment); }
    public ProductParams paramsFor(String sku) { return params.get(sku); }
}
