package pe.edu.vallegrande.vg_ms_casas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("inventory_consumption")  // Mantiene la relación con la tabla
public class Inventory {

    @Id
    @Column("id_inventory")
    private Integer idInventory;        // id_inventory en la base

    @Column("product_id")
    private Integer productId;          // id_product en la base

    @Column("initial_stock")
    private Integer initialStock;       // Stock inicial

    @Column("current_stock")
    private Integer currentStock;       // Stock actual

    private String status;              // 'A' = activo, 'I' = inactivo


}
