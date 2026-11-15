package com.logistpulse.core.observer;

/** Suscriptor 3: avisa a PULSEFLEET (simulado) cuando un SKU se agota. */
public class FleetListener implements StockListener {
    @Override public void onStockChanged(StockChanged e) {
        if (e.currentAvailable() == 0)
            System.out.println("   [PULSEFLEET] " + e.sku() + " agotado: reprogramar rutas de despacho");
    }
}
