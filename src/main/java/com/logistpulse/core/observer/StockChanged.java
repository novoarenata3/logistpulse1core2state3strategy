package com.logistpulse.core.observer;

/** Evento publicado por el inventario cuando cambia el stock disponible de un SKU. */
public record StockChanged(String sku, int previousAvailable, int currentAvailable, String reason) { }
