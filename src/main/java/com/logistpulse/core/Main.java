package com.logistpulse.core;

import com.logistpulse.core.observer.*;
import com.logistpulse.core.state.Lot;
import com.logistpulse.core.strategy.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        // Strategy: politicas por producto
        PolicyRegistry policies = new PolicyRegistry(new FifoPolicy(), new MinMaxPolicy());
        policies.configure("LECHE", new FefoPolicy(), new EoqPolicy(),
                new ProductParams(3650, 10, 3, 50, 2, 5, 10, 100));
        policies.configure("TORNILLO", new LifoPolicy(), new ReorderPointPolicy(),
                new ProductParams(5000, 14, 2, 20, 1, 10, 20, 200));

        // Observer: suscriptores
        StockPublisher publisher = new StockPublisher();
        publisher.subscribe(new LedgerListener());
        publisher.subscribe(new ReplenishmentListener(policies));
        publisher.subscribe(new FleetListener());

        InventoryService inv = new InventoryService(publisher, policies);
        inv.receive(new Lot("L1", "LECHE", 20, today.plusDays(10), 1));
        inv.receive(new Lot("L2", "LECHE", 15, today.plusDays(3), 2));
        inv.receive(new Lot("T1", "TORNILLO", 40, today.plusYears(5), 3));
        inv.receive(new Lot("T2", "TORNILLO", 10, today.plusYears(5), 4));

        System.out.println("== State: Recibido -> Almacenado ==");
        for (String id : new String[]{"L1", "L2", "T1", "T2"}) inv.store(id);

        System.out.println("\n== Strategy FEFO: LECHE reserva primero el que vence antes (L2) ==");
        System.out.println("   elegido: " + inv.reserveNext("LECHE"));

        System.out.println("\n== Strategy LIFO: TORNILLO reserva el ultimo en llegar (T2) ==");
        System.out.println("   elegido: " + inv.reserveNext("TORNILLO"));

        System.out.println("\n== State: Cuarentena como desvio ==");
        inv.quarantine("L1");
        inv.releaseQuarantine("L1");

        System.out.println("\n== State: transicion invalida lanza excepcion ==");
        try { inv.ship("L1"); } // ALMACENADO -> ENVIADO no esta permitido
        catch (IllegalStateException e) { System.out.println("   " + e.getMessage()); }

        System.out.println("\n== Observer: agotar TORNILLO dispara reposicion y PULSEFLEET ==");
        inv.reserveNext("TORNILLO");
    }
}
