package com.sevitours.demo.guide;

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
