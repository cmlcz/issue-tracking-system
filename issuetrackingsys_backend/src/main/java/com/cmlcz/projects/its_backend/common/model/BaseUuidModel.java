package com.cmlcz.projects.its_backend.common.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseUuidModel implements Serializable {

    @Id
    @UuidGenerator
    @Column(name="id", updatable=false, nullable=false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique identifier generated by the server.")
    private UUID id;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique identifier generated by the server.")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique identifier generated by the server.")
    private LocalDateTime lastModificationDate;

}
