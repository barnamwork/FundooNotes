package com.fundoonotes.dto.response;

public class NoteResponseDto {

    private Long id;
    private String title;
    private String description;
    private boolean pinned;
    private boolean archived;
    private boolean trashed;

    public NoteResponseDto() {
    }

    public NoteResponseDto(Long id, String title, String description,
                           boolean pinned, boolean archived, boolean trashed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pinned = pinned;
        this.archived = archived;
        this.trashed = trashed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public void setTrashed(boolean trashed) {
        this.trashed = trashed;
    }
}