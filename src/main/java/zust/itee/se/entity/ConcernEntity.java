package zust.itee.se.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="concern")
public class ConcernEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="concerner_id",insertable = false,updatable = false)
    private UserEntity concernerUser;     //关注者id映射到user表

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="concerned_id",insertable = false,updatable = false)
    private UserEntity  concernedUser;    //被关注者id的映射
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="concerner_id")
    private int concernerId;
    @Column(name="concerned_id")
    private int concernedId;
    @Column(name="concern_time")
    private String concernTime;
}
