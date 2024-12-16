package com.service.main.dto;

public class FileDetails {
    private String filename;
    private long size;
    private byte[] content;

    // Constructors
    public FileDetails(String filename, long size, byte[] content) {
        this.filename = filename;
        this.size = size;
        this.content = content;
    }

    // Getters and setters
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
