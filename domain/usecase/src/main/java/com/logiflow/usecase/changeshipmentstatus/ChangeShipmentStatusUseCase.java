package com.logiflow.usecase.changeshipmentstatus;

import com.logiflow.model.shipment.Shipment;
import com.logiflow.model.shipment.ShipmentStatus;
import com.logiflow.model.shipment.gateways.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ChangeShipmentStatusUseCase {

    private final ShipmentRepository shipmentRepository;

    public Mono<Shipment> changeStatus(String shipmentId, ShipmentStatus newStatus) {
        return shipmentRepository.findById(shipmentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Shipment not found")))
                .map(shipment -> {
                    switch (newStatus) {
                        case DELIVERED -> shipment.deliver();
                        case INCIDENT -> shipment.markIncident();
                        default -> {}
                    }
                    return shipment;
                })
                .flatMap(shipmentRepository::save);
    }
}
