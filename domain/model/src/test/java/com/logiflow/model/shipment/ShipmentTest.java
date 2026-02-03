package com.logiflow.model.shipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShipmentTest {

    private final Location origin =
            new Location("New York", "123 Warehouse St");

    private final Location destination =
            new Location("San Francisco", "456 Market Ave");

    @Test
    void should_start_in_transit_state() {
        Shipment shipment = new Shipment("S1", origin, destination);
        assertEquals(ShipmentStatus.IN_TRANSIT, shipment.getStatus());
    }

    @Test
    void should_deliver_from_in_transit() {
        Shipment shipment = new Shipment("S1", origin, destination);
        shipment.deliver();
        assertEquals(ShipmentStatus.DELIVERED, shipment.getStatus());
    }

    @Test
    void delivered_shipment_cannot_have_incident() {
        Shipment shipment = new Shipment("S1", origin, destination);
        shipment.deliver();
        assertThrows(IllegalStateException.class, shipment::markIncident);
    }
}
