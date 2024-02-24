package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(BaseEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,updatable = false)
    protected LocalDateTime insertDateTime;
    @Column(nullable = false,updatable = false)
    protected Long insertUserId;
    @Column(nullable = false)
    protected LocalDateTime lastUpdateDateTime;
    @Column(nullable = false)
    protected Long lastUpdateUserId;
    private Boolean isDeleted=false;


}
