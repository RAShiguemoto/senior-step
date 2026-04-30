package com.seniorstep.planner.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seniorstep.planner.domain.model.StudySlot;

public interface StudySlotRepository extends JpaRepository<StudySlot, Long>{

	@Query("SELECT COUNT(s) > 0 FROM StudySlot s " +
	           "WHERE s.startDateTime < :end " +
	           "AND s.endDateTime > :start")
	boolean existsOverlapping(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
	Page<StudySlot> findByCompleted(boolean completed, Pageable pageable);

	List<StudySlot> findByStartDateTimeBeforeAndNotificationSentFalse(LocalDateTime windowEnd);
}
