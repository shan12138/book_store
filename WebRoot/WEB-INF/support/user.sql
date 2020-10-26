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
values('С��Ů1��','2016215156','��³��','Ů','34082419980714064X','����ʡ������','18298298187',1000.0,1);
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('С��Ů2��','2016215142','����','Ů','340824199807140111','�ӱ�ʡ������','18356500298',200.0,1);
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('С��Ů3��','2016215144','����','Ů','340824199807140222','���ɹ�����ʯ','18324746197',800.0,1);
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('С��Ů4��','2016215151','����','Ů','340824199807140333','����ʡ̩����','15061081589',600.0,1);
select *from users;
update users set password='123456' where uid=1;
insert into users(username,password,name,gender,idcard,address,telno,balance,status) 
values('С��Ů4��','2016215151','����','Ů','340824199807140933','����ʡ̩����','15061081589',600.0,1);
alter table users change uid uid int not null auto_increment;
desc  users;