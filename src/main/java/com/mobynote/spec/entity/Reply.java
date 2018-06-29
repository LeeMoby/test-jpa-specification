package com.mobynote.spec.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private boolean visible;
    private String email;
    @ManyToOne
    private Article article;
}
