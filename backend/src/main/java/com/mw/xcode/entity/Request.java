package com.mw.xcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue
    private Long id;
    private String currency;
    private String name;
    @Column(name = "`date`") // Escaping the reserved keyword
    private LocalDateTime date;

    @Column(name = "`value`") // Escaping the reserved keyword
    private Double value;

}
