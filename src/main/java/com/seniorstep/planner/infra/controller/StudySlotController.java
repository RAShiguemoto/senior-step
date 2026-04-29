package com.seniorstep.planner.infra.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seniorstep.planner.domain.service.StudySlotService;
import com.seniorstep.planner.infra.controller.dto.StudySlotRequest;
import com.seniorstep.planner.infra.controller.dto.StudySlotResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/study-slots")
@RequiredArgsConstructor
public class StudySlotController {

	private final StudySlotService service;

    @PostMapping
    public ResponseEntity<StudySlotResponse> create(@RequestBody @Valid StudySlotRequest request) {
    	StudySlotResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
        
    @GetMapping
    public ResponseEntity<Page<StudySlotResponse>> getAll(
            @RequestParam(required = false) Boolean completed,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(completed, pageable));
    }
    
    @PatchMapping("/{id}/complete")
    public ResponseEntity<StudySlotResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(service.complete(id));
    }
}
