package com.interview.zerologics.demo.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "follow")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne(fetch = FetchType.LAZY)
    User fromUser;

    @OneToOne(fetch = FetchType.LAZY)
    User toUser;

}
