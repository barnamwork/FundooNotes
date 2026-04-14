package com.fundoonotes.controller;

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;
import com.fundoonotes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponseDto> createNote(
            @Valid @RequestBody NoteRequestDto dto,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(noteService.createNote(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> getAllNotes(
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(noteService.getAllNotes(token));
    }

    @PatchMapping("/{id}/pin")
    public ResponseEntity<NoteResponseDto> pin(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(noteService.pinNote(id, token));
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<NoteResponseDto> archive(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(noteService.archiveNote(id, token));
    }

    @PatchMapping("/{id}/trash")
    public ResponseEntity<NoteResponseDto> trash(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(noteService.trashNote(id, token));
    }
}