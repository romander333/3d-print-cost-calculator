package com.roman.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE printers SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "printers")
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "model")
    private String model;
    @Column(name = "power_consumption_watts")
    private double powerConsumptionWatts;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.IDLE;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_spool_id")
    private Spool currentSpool;
    @Column(nullable = false, name = "max_width")
    private int maxWidth;
    @Column(nullable = false, name = "max_depth")
    private int maxDepth;
    @Column(nullable = false, name = "max_height")
    private int maxHeight;
    @Column(name = "total_print_hours")
    private double totalPrintHours;
    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted;

    public enum Status {
        IDLE,
        PRINTING,
        PAUSED,
        MAINTENANCE,
        OFFLINE
    }
}
