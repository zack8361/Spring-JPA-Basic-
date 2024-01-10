package jpabasic.ex1hellojpa.Entity;


import jakarta.persistence.*;
import jpabasic.ex1hellojpa.enumc.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@Entity
@Getter
@Setter
public class NewMember {


    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "user_age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDate testLocalDate;

    private LocalDateTime testLocalDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
