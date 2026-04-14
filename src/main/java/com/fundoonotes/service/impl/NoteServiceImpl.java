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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

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

    @Override
    public NoteResponseDto createNote(NoteRequestDto dto, String token) {

        log.info("Creating note");

        Long userId = tokenUtil.getUserIdFromToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        note.setUser(user);

        Note saved = noteRepository.save(note);

        return mapToResponse(saved);
    }

    @Override
    public List<NoteResponseDto> getAllNotes(String token) {

        Long userId = tokenUtil.getUserIdFromToken(token);

        return noteRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NoteResponseDto pinNote(Long noteId, String token) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setPinned(!note.isPinned());

        log.info("Toggled pin for note {}", noteId);

        return mapToResponse(noteRepository.save(note));
    }

    @Override
    public NoteResponseDto archiveNote(Long noteId, String token) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setArchived(!note.isArchived());

        log.info("Toggled archive for note {}", noteId);

        return mapToResponse(noteRepository.save(note));
    }

    @Override
    public NoteResponseDto trashNote(Long noteId, String token) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTrashed(!note.isTrashed());

        log.info("Toggled trash for note {}", noteId);

        return mapToResponse(noteRepository.save(note));
    }

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