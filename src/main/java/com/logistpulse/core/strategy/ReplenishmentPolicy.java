package com.logistpulse.core.strategy;

/** Strategy 2: cuanto reponer dado el stock disponible. Devuelve 0 si no hay que pedir. */
public interface ReplenishmentPolicy {
    String name();
    int orderQuantity(int available, ProductParams p);
}
