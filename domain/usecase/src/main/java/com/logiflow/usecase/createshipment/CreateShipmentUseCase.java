package com.logiflow.usecase.createshipment;

import com.logiflow.model.shipment.Shipment;
import com.logiflow.model.shipment.gateways.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateShipmentUseCase {

    private final ShipmentRepository shipmentRepository;

    public Mono<Shipment> create(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }
}
