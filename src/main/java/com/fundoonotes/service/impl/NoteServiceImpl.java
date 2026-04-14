package com.fundoonotes.service.impl;

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;
import com.fundoonotes.entity.Note;
import com.fundoonotes.entity.User;
import com.fundoonotes.exception.UserNotFoundException;
import com.fundoonotes.repository.NoteRepository;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.NoteService;
import com.fundoonotes.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    public NoteServiceImpl(NoteRepository noteRepository,
                           UserRepository userRepository,
                           TokenUtil tokenUtil) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    // 🔥 CREATE NOTE
    @Override
    public NoteResponseDto createNote(NoteRequestDto dto, String token) {

        // 1. Get userId from token
        Long userId = tokenUtil.getUserIdFromToken(token);

        // 2. Find user in DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // 3. Create note object
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        note.setUser(user);

        // 4. Save to DB
        Note savedNote = noteRepository.save(note);

        // 5. Convert to response
        return mapToResponse(savedNote);
    }

    // 🔥 GET ALL NOTES
    @Override
    public List<NoteResponseDto> getAllNotes(String token) {

        // 1. Get userId
        Long userId = tokenUtil.getUserIdFromToken(token);

        // 2. Fetch notes from DB
        List<Note> notes = noteRepository.findByUserId(userId);

        // 3. Convert list → response DTO
        return notes.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NoteResponseDto pinNote(Long noteId, String token) {
        return null;
    }

    @Override
    public NoteResponseDto archiveNote(Long noteId, String token) {
        return null;
    }

    @Override
    public NoteResponseDto trashNote(Long noteId, String token) {
        return null;
    }

    // 🔥 HELPER METHOD (convert entity → DTO)
    private NoteResponseDto mapToResponse(Note note) {
        return new NoteResponseDto(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.isPinned(),
                note.isArchived(),
                note.isTrashed()
        );
    }
}