package com.ms.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "country_code" )
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}