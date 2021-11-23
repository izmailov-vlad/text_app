package ru.miracle.madmeditation.data.models;

import java.util.List;

import ru.miracle.madmeditation.domain.entities.FeelingsDto;

public class FeelingsResponse {
    public List<FeelingsDto> data;
    public String success;

    public FeelingsResponse(String success, List<FeelingsDto> feelingsData) {
        this.data = feelingsData;
        this.success = success;
    }
}
