package ru.miracle.madmeditation.data.models;

import java.util.List;

import ru.miracle.madmeditation.domain.entities.QuotesDto;

public class QuotesResponse {
    public List<QuotesDto> data;
    public String success;


    public QuotesResponse(List<QuotesDto> quotesData, String success) {
        this.data = quotesData;
        this.success = success;
    }

    public List<QuotesDto> getQuotesData() {
        return data;
    }
}

