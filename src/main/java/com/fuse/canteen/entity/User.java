package com.fuse.canteen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_USER_USER_NAME", columnNames = "userName")
})
public class User extends BaseEntity{

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
    @SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "email",unique = true)
    private String email;
    private String password;


    private String firstName;
    private String middleName;
    private String lastName;

    private Long phoneNumber;


    private Employee employee;

    @ManyToMany
    @JoinTable(name = "UsersRoles",
            joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "id"))
    private Collection<Role> roles;

}
