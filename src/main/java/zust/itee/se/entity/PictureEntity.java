package zust.itee.se.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="picture")
public class PictureEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",insertable = false,updatable = false)
    private UserEntity userEntity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String fname;
    @Column(name="user_id")
    private int userId;
    @Column
    private String intro;
    @Column
    private String tags;
    @Column(name="upload_time")
    private String uploadTime;
    @Column(name="click_num")
    private int clickNum;

}
