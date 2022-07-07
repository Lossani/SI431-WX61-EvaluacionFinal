package com.gamingworld.app.gamingworld.shared.inbound.externalapi.domain.model.entity;

import com.gamingworld.app.gamingworld.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "external_apis")
public class ExternalAPI extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date expirationDate;
}
