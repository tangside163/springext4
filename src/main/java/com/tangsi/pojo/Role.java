package com.tangsi.pojo;


import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "auth_role")
public class Role implements Serializable{

    private static final long serialVersionUID = 6177417450707400228L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="roleid")
    private Long id;
    @Column
    private String name; 
    @Column
    private String description;
    
    @ManyToMany(mappedBy = "roles",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @Basic(fetch = FetchType.LAZY)
    private Collection<User> users;
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name = "auth_role_permission", 
           joinColumns = { @JoinColumn(name = "fk_roleid",referencedColumnName="roleid") }, 
           inverseJoinColumns = { @JoinColumn(name = "fk_pmsid",referencedColumnName="pmsid") })
    private Collection<Permission> pmss;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Collection<User> getUsers() {
        return users;
    }
    public void setUsers(Collection<User> users) {
        this.users = users;
    }
    public Collection<Permission> getPmss() {
        return pmss;
    }
    public void setPmss(Collection<Permission> pmss) {
        this.pmss = pmss;
    }
}
