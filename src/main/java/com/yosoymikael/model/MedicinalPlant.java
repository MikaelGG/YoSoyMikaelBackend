package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicinal_plants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicinalPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicinal_plant_id")
    private Long medicinalPlantId;

    @Column(name = "medicinal_name", nullable = false)
    private String medicinalName;

    @Column(name = "medicinal_image")
    private String medicinalImage;

    @Column(name = "medicinal_description", columnDefinition = "TEXT")
    private String medicinalDescription;

    @Column(name = "medicinal_price", precision = 10, scale = 2)
    private BigDecimal medicinalPrice;

    @Column(name = "medicinal_spots")
    private Integer medicinalSpots;

    @OneToMany(mappedBy = "medicinalPlant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<MedicinalPlantReservation> reservations = new ArrayList<>();

    public void addReservation(MedicinalPlantReservation reservation) {
        reservations.add(reservation);
        reservation.setMedicinalPlant(this);
    }

    public void removeReservation(MedicinalPlantReservation reservation) {
        reservations.remove(reservation);
        reservation.setMedicinalPlant(null);
    }
}
