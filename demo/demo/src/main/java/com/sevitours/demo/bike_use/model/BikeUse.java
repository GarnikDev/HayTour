package com.sevitours.demo.bike_use.model;

import com.sevitours.demo.bicycle.model.Bicycle;
import com.sevitours.demo.common.enums.Condition;
import com.sevitours.demo.common.enums.Source;
import com.sevitours.demo.rental.model.Rental;
import com.sevitours.demo.tour.model.Tour;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Table(name = "\"Bike_Use\"")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"tour", "rental", "bicycle"})  // avoid stack overflow in toString
public class BikeUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    // Discriminator – tells us what kind of usage this is
    @Enumerated(EnumType.STRING)
    @Column(name = "\"use_type\"", nullable = false, length = 20)
    private Source useType;

    // Nullable FK – only one of tour or rental should be set
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Tour_id\"")
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Rental_id\"")
    private Rental rental;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Bicycle_id\"", nullable = false)
    private Bicycle bicycle;

    @Column(name = "\"assigned_at\"", nullable = false)
    private OffsetDateTime assignedAt;

    @Column(name = "\"returned_at\"")
    private OffsetDateTime returnedAt;

    @Column(name = "\"bike_condition_before\"", nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition conditionBefore;

    @Column(name = "\"bike_condition_after\"")
    @Enumerated(EnumType.STRING)
    private Condition conditionAfter;

    @Column(name = "\"notes\"")
    private String notes;

    // Optional: helper methods to make usage cleaner
    public boolean isTour() {
        return useType == Source.TOUR;
    }

    public boolean isRental() {
        return useType == Source.RENTAL;
    }

    // You can add validation logic here or in service layer
}