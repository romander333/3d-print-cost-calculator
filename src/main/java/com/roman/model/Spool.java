package com.roman.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "spools")
public class Spool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;
    @OneToOne(mappedBy = "currentSpool")
    private Printer currentPrinter;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "current_weight", nullable = false)
    private Integer currentWeight;
    @Column(name = "initial_weight", nullable = false)
    private Integer initialWeight;
    @Column(name = "purchase_date", nullable = false)
    @Builder.Default
    private LocalDate purchaseDate = LocalDate.now();
    @Column(nullable = false)
    @Builder.Default
    private boolean active = Boolean.TRUE;
}
