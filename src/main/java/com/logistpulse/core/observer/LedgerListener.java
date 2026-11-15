package com.logistpulse.core.observer;

import java.util.ArrayList;
import java.util.List;

/** Suscriptor 1: registra cada cambio (ledger). */
public class LedgerListener implements StockListener {
    private final List<String> entries = new ArrayList<>();
    @Override public void onStockChanged(StockChanged e) {
        String line = e.sku() + ": " + e.previousAvailable() + " -> " + e.currentAvailable() + " (" + e.reason() + ")";
        entries.add(line);
        System.out.println("   [LEDGER] " + line);
    }
    public List<String> entries() { return List.copyOf(entries); }
}
