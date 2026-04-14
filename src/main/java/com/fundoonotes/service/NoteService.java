package com.fundoonotes.service;

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;

import java.util.List;

public interface NoteService {

    NoteResponseDto createNote(NoteRequestDto requestDto, String token);

    List<NoteResponseDto> getAllNotes(String token);

    NoteResponseDto pinNote(Long noteId, String token);

    NoteResponseDto archiveNote(Long noteId, String token);

    NoteResponseDto trashNote(Long noteId, String token);
}