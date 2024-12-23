package org.innercircle.surveyapiapplication.domain.surveyItem.infrastructure;

import org.innercircle.surveyapiapplication.domain.surveyItem.domain.SurveyItem;

import java.util.List;

public interface SurveyItemRepository {

    SurveyItem save(SurveyItem surveyItem);

    List<SurveyItem> findLatestSurveyItemsBySurveyId(Long surveyId);

    SurveyItem findByIdAndVersion(Long surveyId, Long questionId, int version);

    SurveyItem findLatestSurveyItemBySurveyIdAndSurveyItemId(Long surveyId, Long questionId);

    List<SurveyItem> findBySurveyId(Long surveyId);

}
