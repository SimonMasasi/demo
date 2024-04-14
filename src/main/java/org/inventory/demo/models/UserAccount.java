package org.inventory.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_account")
@SQLDelete(sql = "UPDATE user_account SET deteted=true WHERE id = ?", check = ResultCheckStyle.COUNT)
@SQLRestriction("is_deleted <> true")
public class UserAccount extends  BaseEntity  implements Serializable {
    @Column(name = "frist_name" , nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private  String middleName;

    @Column(name = "email" , unique = true)
    private  String email;

    @Column(name = "user_name" , unique = true)
    private  String userName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

}
