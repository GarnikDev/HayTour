package com.sevitours.demo.guide.services;

import com.sevitours.demo.guide.model.Guide;
import lombok.Getter;

@Getter
public class UpdateGuideCommand {
    private Integer id;
    private Guide guide;

    public UpdateGuideCommand(Integer id, Guide guide) {
        this.id = id;
        this.guide = guide;
    }
}
