package com.tangsi.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Table(name = "auth_user")
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5196555485281228861L;

	@Id
	@Column(name = "userid")
	@GenericGenerator(name = "useridgenerator", strategy = "native")
	@GeneratedValue(generator = "useridgenerator", strategy = GenerationType.IDENTITY /* 主键由数据库自动生成 */)
	private long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name = "auth_user_role", joinColumns = { @JoinColumn(name = "fk_userid", referencedColumnName = "userid") }, inverseJoinColumns = { @JoinColumn(name = "fk_roleid", referencedColumnName = "roleid") })
	private List<Role> roles;

	@Column
	private String nickName;

	/**
	 * 锁定时间
	 */
	@Column
	private long lockedAt;

	/**
	 * 密码错误次数
	 */
	private int errorTimes;

	public long getLockedAt() {
		return lockedAt;
	}

	public void setLockedAt(long lockedAt) {
		this.lockedAt = lockedAt;
	}

	public int getErrorTimes() {
		return errorTimes;
	}

	public void setErrorTimes(int errorTimes) {
		this.errorTimes = errorTimes;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
