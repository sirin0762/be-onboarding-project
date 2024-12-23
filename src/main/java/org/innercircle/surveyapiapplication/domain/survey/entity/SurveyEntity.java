package org.innercircle.surveyapiapplication.domain.survey.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.innercircle.surveyapiapplication.domain.surveyItem.domain.SurveyItem;
import org.innercircle.surveyapiapplication.domain.survey.domain.Survey;
import org.innercircle.surveyapiapplication.global.entity.BaseEntity;

import java.util.List;

@Table(name = "surveys")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SurveyEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 1000, nullable = true)
    private String description;

    @Version
    private Integer version;

    public SurveyEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static SurveyEntity from(Survey survey) {
        return new SurveyEntity(
            survey.getName(),
            survey.getDescription()
        );
    }

    public Survey toDomain(List<SurveyItem> surveyItems) {
        return new Survey(this.id, this.name, this.description, surveyItems);
    }

}
