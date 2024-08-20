package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Organizations.University;
import org.twspring.capstone2.Service.Imp.UniversityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/university")
public class UniversityController {
    private final UniversityService universityService;

    //===========================GET===========================

    @GetMapping("/get/all")
    public ResponseEntity getAllUniversities() {
        return ResponseEntity.status(200).body(universityService.getAllUniversities());
    }

    @GetMapping("/students/{universityId}/{universityStaffId}")
    public ResponseEntity getAllStudents(
            @PathVariable Integer universityId,
            @PathVariable Integer universityStaffId
    ) {
        return ResponseEntity.status(200).body(universityService.getAllStudents(universityId, universityStaffId));
    }

    @GetMapping("/suggested-opportunities/{universityId}/{studentId}")
    public ResponseEntity getSuggestedOpportunitiesForStudents(
            @PathVariable Integer universityId,
            @PathVariable Integer studentId) {
        return ResponseEntity.status(200).body(universityService.getSuggestedOpportunitiesForStudents(universityId, studentId));
    }

    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addUniversity(
            @Valid @RequestBody University university) {
        universityService.addUniversity(university);
        return ResponseEntity.status(201).body(new ApiResponse("University added successfully"));
    }
    //===========================PUT===========================
    @PutMapping("/update/{university_id}")
    public ResponseEntity updateUniversity(@PathVariable Integer university_id,
                                           @Valid @RequestBody University university) {
        universityService.updateUniversity(university_id, university);
        return ResponseEntity.status(200).body(new ApiResponse("University updated successfully"));
    }
    @PutMapping("/add-student/{universityId}/{volunteerId}/{universityStaffId}")
    public ResponseEntity addStudentToUniversity(
            @PathVariable Integer universityId,
            @PathVariable Integer universityStaffId,
            @PathVariable Integer volunteerId) {
        universityService.addStudentToUniversity(universityId, universityStaffId, volunteerId);
        return ResponseEntity.status(201).body(new ApiResponse("Student added successfully"));
    }
    @PutMapping("/add-suggested-opportunity/{universityId}/{universityStaffId}/{opportunityId}")
    public ResponseEntity addSuggestedOpportunity(
            @PathVariable Integer universityId,
            @PathVariable Integer universityStaffId,
            @PathVariable Integer opportunityId) {
        universityService.addSuggestedOpportunityToUniversity(universityId, universityStaffId, opportunityId);
        return ResponseEntity.status(201).body(new ApiResponse("Opportunity suggested successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{university_id}")
    public ResponseEntity deleteUniversity(@PathVariable Integer university_id) {
        universityService.deleteUniversity(university_id);
        return ResponseEntity.status(200).body(new ApiResponse("University deleted successfully"));
    }
}