package com.seniorstep.planner.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "study_slot")
public class StudySlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank(message = "Title is mandatory!")
	@Column(nullable = false)
	private String title;
	
	@NotNull(message = "Start time is mandatory")
	@Column(name = "start_date_time", nullable = false)
	private LocalDateTime startDateTime;
	
	@NotNull(message = "End time is mandatory")
    @Column(name = "end_date_time", nullable = false)
    private LocalDateTime endDateTime;
	
	private boolean completed = false;
	
	private boolean notificationSent = false;
	
}
