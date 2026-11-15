package com.logistpulse.core.strategy;

/** Parametros de Investigacion de Operaciones de un producto. */
public record ProductParams(double annualDemand, double dailyDemand, int leadTimeDays,
                            double orderCost, double holdingCost,
                            int safetyStock, int minLevel, int maxLevel) {
    /** Punto de reorden = demanda diaria x lead time + stock de seguridad. */
    public double reorderPoint() { return dailyDemand * leadTimeDays + safetyStock; }
}
