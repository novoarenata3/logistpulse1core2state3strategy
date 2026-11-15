package com.logistpulse.core.state;

import java.time.LocalDate;

/** Contexto del patron State: delega las transiciones en el estado activo. */
public class Lot {
    private final String id, sku;
    private final int quantity;
    private final LocalDate expiry;
    private final long receivedSeq; // orden de llegada (para FIFO/LIFO)
    private LotState state = new ReceivedState();

    public Lot(String id, String sku, int quantity, LocalDate expiry, long receivedSeq) {
        this.id = id; this.sku = sku; this.quantity = quantity; this.expiry = expiry; this.receivedSeq = receivedSeq;
    }

    public void store()             { state = state.store(); }
    public void reserve()           { state = state.reserve(); }
    public void ship()              { state = state.ship(); }
    public void quarantine()        { state = state.quarantine(); }
    public void releaseQuarantine() { state = state.releaseQuarantine(); }

    public String getId() { return id; }
    public String getSku() { return sku; }
    public int getQuantity() { return quantity; }
    public LocalDate getExpiry() { return expiry; }
    public long getReceivedSeq() { return receivedSeq; }
    public LotState getState() { return state; }
    public boolean isAvailable() { return state.isAvailable(); }

    @Override public String toString() { return id + "(" + sku + " x" + quantity + " " + state.name() + ")"; }
}
