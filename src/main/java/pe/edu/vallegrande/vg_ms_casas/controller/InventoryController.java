package pe.edu.vallegrande.vg_ms_casas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_casas.model.Inventory;
import pe.edu.vallegrande.vg_ms_casas.service.InventoryService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public Flux<Inventory> getAll() {
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Inventory> getById(@PathVariable Integer id) {
        return inventoryService.findById(id);
    }

    @PostMapping
    public Mono<Inventory> create(@RequestBody Inventory inventory) {
        return inventoryService.save(inventory);
    }

    @PutMapping("/{id}")
    public Mono<Inventory> update(@PathVariable Integer id, @RequestBody Inventory inventory) {
        return inventoryService.update(id, inventory);
    }

    @PutMapping("/{id}/deactivate")
    public Mono<Void> deactivate(@PathVariable Integer id) {
        return inventoryService.delete(id);
    }

    @PutMapping("/{id}/restore")
    public Mono<Inventory> restore(@PathVariable Integer id) {
        return inventoryService.restore(id);
    }

    @GetMapping("/active")
    public Flux<Inventory> listActive() {
        return inventoryService.findActive();
    }

    @GetMapping("/inactive")
    public Flux<Inventory> listInactive() {
        return inventoryService.findInactive();
    }
}
