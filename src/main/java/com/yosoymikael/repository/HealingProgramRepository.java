package com.yosoymikael.repository;

import com.yosoymikael.model.HealingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealingProgramRepository extends JpaRepository<HealingProgram, Long> {
}
