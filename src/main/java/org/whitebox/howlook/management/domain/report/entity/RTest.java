package org.whitebox.howlook.management.domain.report.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Setter
public class RTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RTestId;

    private String name;

    private Long memberId;

    private String guitar;
}
