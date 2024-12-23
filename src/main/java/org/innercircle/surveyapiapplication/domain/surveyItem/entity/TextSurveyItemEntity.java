package org.innercircle.surveyapiapplication.domain.surveyItem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.innercircle.surveyapiapplication.domain.surveyItem.domain.TextSurveyItem;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("TEXT")
public class TextSurveyItemEntity extends SurveyItemEntity {

    public TextSurveyItemEntity(Long surveyItemId, int surveyItemVersion, String name, String description, boolean required, Long surveyId) {
        super(surveyItemId, surveyItemVersion, name, description, required, surveyId);
    }

    @Override
    public TextSurveyItem toDomain() {
        return new TextSurveyItem(
            this.getSurveyItemId().getId(),
            this.getSurveyItemId().getVersion(),
            this.getName(),
            this.getDescription(),
            this.isRequired(),
            this.getSurveyId()
        );
    }

}
