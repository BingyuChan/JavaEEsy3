package zust.itee.se.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="user")
public class UserEntity {
    @OneToMany(cascade  = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "userEntity")
    private Set<PictureEntity> pictureEntities;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "concernerUser")
    private Set<ConcernEntity> concernerUsers;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "concernedUser")
    private Set<ConcernEntity> concernedUsers;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String intro;
    @Column
    private String gender;
    @Column
    private String province;
    @Column
    private String city;
    @Column(name="regist_time")
    private String registTime;
    @Column
    private String type;
    @Column
    private String email;
    @Column
    private String mobile;
    @Column(name="QQ")
    private int qq;
    @Column
    private String status;
}
