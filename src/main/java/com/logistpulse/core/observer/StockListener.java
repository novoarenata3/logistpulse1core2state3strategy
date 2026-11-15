package com.logistpulse.core.observer;

/** Interfaz comun de los suscriptores. */
public interface StockListener {
    void onStockChanged(StockChanged event);
}
