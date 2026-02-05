package com.roman.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@SQLDelete(sql = "UPDATE materials SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "materials")
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "material_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType type;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "filament_diameter", nullable = false)
    @Builder.Default
    private Double filamentDiameter = 1.75;
    @Column(name = "density", nullable = false)
    private Double density;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;
    @Column(name = "spools_quantity")
    @Builder.Default
    private Integer quantity = 1;
    @Column(name = "is_active")
    @Builder.Default
    private boolean isActive = Boolean.TRUE;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    public enum MaterialType {
        PLA, PETG, ABS, TPE, METAL, TPU, NYLON, ASA
    }
}
