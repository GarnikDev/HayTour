package com.sevitours.demo.guide.services;

import com.sevitours.demo.guide.model.Guide;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateGuideCommand {
    private UUID id;
    private Guide guide;

    public UpdateGuideCommand(UUID id, Guide guide) {
        this.id = id;
        this.guide = guide;
    }
}
