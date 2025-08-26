package pe.edu.vallegrande.vg_ms_casas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_casas.model.Inventory;
import pe.edu.vallegrande.vg_ms_casas.repository.InventoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Flux<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Mono<Inventory> findById(Integer id) {
        return inventoryRepository.findById(id);
    }

    public Mono<Inventory> save(Inventory inventory) {
        if (inventory.getStatus() == null) {
            inventory.setStatus("A"); // activo por defecto
        }
        return inventoryRepository.save(inventory);
    }

    public Mono<Inventory> update(Integer id, Inventory inventory) {
        return inventoryRepository.findById(id)
                .flatMap(existingInventory -> {
                    existingInventory.setProductId(inventory.getProductId());
                    existingInventory.setInitialStock(inventory.getInitialStock());
                    existingInventory.setCurrentStock(inventory.getCurrentStock());
                    existingInventory.setStatus(inventory.getStatus());
                    return inventoryRepository.save(existingInventory);
                });
    }

    // Eliminación lógica
    public Mono<Void> delete(Integer id) {
        return inventoryRepository.findById(id)
                .flatMap(existingInventory -> {
                    existingInventory.setStatus("I");
                    return inventoryRepository.save(existingInventory);
                }).then();
    }

    // Restaurar inventario
    public Mono<Inventory> restore(Integer id) {
        return inventoryRepository.findById(id)
                .flatMap(existingInventory -> {
                    existingInventory.setStatus("A");
                    return inventoryRepository.save(existingInventory);
                });
    }

    // Listar solo activos
    public Flux<Inventory> findActive() {
        return inventoryRepository.findAll()
                .filter(inv -> "A".equalsIgnoreCase(inv.getStatus()));
    }

    // Listar solo inactivos
    public Flux<Inventory> findInactive() {
        return inventoryRepository.findAll()
                .filter(inv -> "I".equalsIgnoreCase(inv.getStatus()));
    }
}
