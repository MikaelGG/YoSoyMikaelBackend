package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "healing_program_reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealingProgramReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "healing_program_reservation_id")
    private Long healingProgramReservationId;

    @Column(name = "patients_full_name", nullable = false)
    private String patientsFullName;

    @Column(name = "patients_phone")
    private String patientsPhone;

    @Column(name = "patients_mail")
    private String patientsMail;

    @Column(name = "patients_address")
    private String patientsAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "healing_program_id", nullable = false)
    @JsonBackReference
    private HealingProgram healingProgram;
}
