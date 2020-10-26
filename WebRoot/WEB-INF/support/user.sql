drop table users;
create table users(
	uid int primary key auto_increment,
	username varchar(20) not null,
	password varchar(20) not null,
	name varchar(20) not null,
	gender varchar(20) not null,
	idcard varchar(20) unique,
	address varchar(20),
	telno varchar(20),
	regtime timestamp default current_timestamp,
	balance double,
	status int,
	images varchar(100) default '/images/head.jpg'
)auto_increment=1;
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('小仙女1号','2016215156','黄鲁菲','女','34082419980714064X','安徽省安庆市','18298298187',1000.0,1);
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('小仙女2号','2016215142','陈姗姗','女','340824199807140111','河北省霸州市','18356500298',200.0,1);
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('小仙女3号','2016215144','金悦','女','340824199807140222','内蒙古牙克石','18324746197',800.0,1);
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('小仙女4号','2016215151','汤瑶','女','340824199807140333','江苏省泰州市','15061081589',600.0,1);
select *from users;
update users set password='123456' where uid=1;
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('小仙女4号','2016215151','汤瑶','女','340824199807140933','江苏省泰州市','15061081589',600.0,1);
alter table users change uid uid int not null auto_increment;
desc  users;