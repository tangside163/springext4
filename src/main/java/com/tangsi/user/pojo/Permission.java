/**
 * tangsi	
 * 2014年4月13日
 */
package com.tangsi.user.pojo;

/**
 * @author Administrator
 *
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "auth_permission")
public class Permission implements Serializable {

	private static final long serialVersionUID = -8792590494605747957L;

	@Id
	@GenericGenerator(name="permissionidgenerator",strategy="native")
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator="permissionidgenerator")
	@Column(name = "pmsid")
	private Long id;
	@Column
	private String name;
	@Column
	private String description;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmss")
	@Basic(fetch = FetchType.LAZY)
	private Collection<Role> roles;

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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
