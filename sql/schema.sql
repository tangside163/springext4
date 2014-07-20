drop table if exists log; 
/*  记录用户 访问日志 */
create table log (

	id bigint(20) primary key auto_increment,
	url varchar(512) default null,
	time bigint(20) default 0,
	message varchar(1024) default null
);

drop table if exists auth_user;

/* 用户表 */
create table auth_user(
	
	userid bigint(20) primary key auto_increment,
	name varchar(512),
	email varchar(512),
	password varchar(512) not null,
	phone char(11),
	nickname varchar(512),
	lockedAt bigint(20) not null comment '锁定时间',
	errorTimes int default 0   comment '密码错误次数'
);

drop table if exists auth_role_permission;
/* 角色 权限表 */
create table auth_role_permission(
	
	id bigint(20) primary key auto_increment,
	fk_roleid bigint(20),
	fk_pmsid bigint(20)

);

drop table if exists auth_user_role;
/* 用户角色关系表 */
create table auth_user_role(
	id bigint(20) primary key auto_increment,
	fk_userid bigint(20),
	fk_roleid bigint(20)
);

drop table if exists auth_role;
/*用户表 */
create table auth_role(
	roleid bigint(20) primary key auto_increment,
	name varchar(512),
	description varchar(512)
);

drop table if exists auth_permission;
/*权限表 */
create table auth_permission(
	pmsid bigint(20) primary key auto_increment,
	name varchar(512),
	description varchar(1024)
);

insert into auth_user(name ,email,password,phone,nickname,lockedAt,errorTimes) values('tangsi','tangside163@163.com','123','18670950325','柔软时光',0,0);
insert into auth_role(	name,description) values('superadmin','超级管理员角色');
insert into auth_permission(	name,description) values(1,'superpermission','这个是超级权限');
insert into auth_user_role values(1,1,1);
insert into auth_role_permission values(1,1,1);

insert into auth_user(name ,email,password,phone,nickname,lockedAt,errorTimes) values('tangdu','tangside163@163.com','123','18670950325','柔软时光',0,0);
insert into auth_role(	name,description) values('commmonadmin','普通管理员角色');
insert into auth_permission(	name,description)  values('commonpermission','这个是普通权限');
insert into auth_user_role values(2,2,2);
insert into auth_role_permission values(2,2,2);

select * from auth_user;

select * from auth_user_role;