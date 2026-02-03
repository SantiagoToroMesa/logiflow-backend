package com.logiflow.model.shipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShipmentTest {

    @Test
    void should_start_in_created_state() {
        Shipment shipment = new Shipment("S1");
        assertEquals(ShipmentStatus.CREATED, shipment.getStatus());
    }

    @Test
    void should_dispatch_from_created() {
        Shipment shipment = new Shipment("S1");
        shipment.dispatch();
        assertEquals(ShipmentStatus.IN_TRANSIT, shipment.getStatus());
    }

    @Test
    void delivered_shipment_cannot_change() {
        Shipment shipment = new Shipment("S1");
        shipment.dispatch();
        shipment.deliver();
        assertThrows(IllegalStateException.class, shipment::markException);
    }

}
