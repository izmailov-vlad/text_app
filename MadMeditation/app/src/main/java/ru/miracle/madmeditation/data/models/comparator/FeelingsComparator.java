package ru.miracle.madmeditation.data.models.comparator;

import java.util.Comparator;

import ru.miracle.madmeditation.domain.entities.FeelingsDto;

public class FeelingsComparator implements Comparator<FeelingsDto> {
    @Override
    public int compare(FeelingsDto feelingsDto1, FeelingsDto feelingsDto2) {
        return feelingsDto1.getPosition().compareTo(feelingsDto2.getPosition());
    }
}
