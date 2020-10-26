drop table orders;

create table orders (oid int primary key auto_increment,
	commitTime timeStamp default  current_timestamp,
	amount double not null,
	uid int not null ,
	foreign key(uid)  references users(uid)
);
select *from orders;


create table orderList(lid int primary key auto_increment,
bname varchar(50) not null,
price double not null,
quantity int not null,
amount double not null,
bid int not null,
oid int not null,
foreign key(bid)  references books(bid)
);
desc orderList;
select *from orderList;
drop table orderList;
insert into orders (oid,amount,uid) values(
100689,199,1);
insert into orders (oid,amount,uid) values(
100690,297,2);
insert into orders (oid,amount,uid) values(
100691,240,3);
insert into orders (oid,amount,uid) values(
100692,150,1);
insert into orders (oid,amount,uid) values(
100693,200,2);
insert into orders (oid,amount,uid) values(
100694,180,3);
insert into orders (oid,amount,uid) values(
100695,380,4);

drop view orders_list;

create view orders_list as
select oid,GROUP_CONCAT(bname) as descp,sum(price) as price , sum(quantity) as quantity,sum(amount) as amount 
from orderList group by oid;

select *from orders_list;

drop view user_order;

create view user_order
as
select name,username,idcard,orders.*,descp from orders,users,orders_list where 
orders.uid=users.uid and orders.oid=orders_list.oid ;


select *from user_order;

