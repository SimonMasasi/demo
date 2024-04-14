package org.inventory.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.UUID;
@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private  LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private  LocalDateTime updatedAt;

    @Basic(optional = true)
    @Column(name = "created_by")
    private  long createdBy;

    @Column(name = "updated_by")
    private  long updatedBy;

    @SQLRestriction("deleted <> false")
    @Column(columnDefinition = "boolean default false" , name = "is_deleted")
    private Boolean deleted = false;

    @Column(columnDefinition = "boolean default true" , name = "is_active")
    @SQLRestriction("active <> true")
    private Boolean active = true;

    @Column(unique = true , name = "unique_id")
    private String uuid = UUID.randomUUID().toString();

}
