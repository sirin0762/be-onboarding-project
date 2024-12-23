package org.innercircle.surveyapiapplication.domain.survey.presentation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.innercircle.surveyapiapplication.domain.survey.domain.Survey;
import org.innercircle.surveyapiapplication.domain.surveyItem.presentation.dto.SurveyItemCreateRequest;

import java.util.List;

@JsonSerialize
public record SurveyCreateRequest(
    String name,
    String description,
    List<SurveyItemCreateRequest> surveyItemCreateRequests
) {
    public Survey toDomain() {
        return new Survey(name, description, surveyItemCreateRequests.stream().map(SurveyItemCreateRequest::toDomain).toList());
    }
}
