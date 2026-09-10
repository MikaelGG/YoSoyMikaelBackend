package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicinal_plant_reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicinalPlantReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicinal_plant_reservation_id")
    private Long medicinalPlantReservationId;

    @Column(name = "patients_full_name", nullable = false)
    private String patientsFullName;

    @Column(name = "patients_phone")
    private String patientsPhone;

    @Column(name = "patients_mail")
    private String patientsMail;

    @Column(name = "patients_address")
    private String patientsAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicinal_plant_id", nullable = false)
    @JsonBackReference
    private MedicinalPlant medicinalPlant;
}
