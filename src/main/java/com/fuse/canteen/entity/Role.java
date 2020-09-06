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
@Table(name = "Roles")
public class Role extends BaseEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_GEN")
    @SequenceGenerator(name = "ROLE_SEQ_GEN", sequenceName = "ROLE_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> user;

    @ManyToMany
    @JoinTable(name = "RolesPrivilege",
            joinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "PrivilegeId", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}
