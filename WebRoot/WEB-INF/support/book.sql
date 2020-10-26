create table books(bid int primary key auto_increment,
bname varchar(60) not null,
image varchar(200),
author varchar(18) not null,
descp varchar(300) not null,
price double not  null,
publisher varchar(80) not null,
publishTime date not null,
type varchar(10)
);

drop table books;
insert into books (bname,image,author,descp,price,publisher,publishTime) values
("从你的全世界路过","/images/pic1.jpg","张嘉佳","6 个月,畅销200万册，
超会讲故事的人张嘉佳，献给你的心动故事",50,"湖南文艺出版社","2016-7-3");
insert into books (bname,image,author,descp,price,publisher,publishTime) values
("从你的全世界路过","/images/pic2.jpg","张嘉佳","超豪华软皮精装！入选
央视2014中国好书的80后作品！十年华语畅销小说，增加9个故事，诞生10部电影",38.8,"机械工艺出版社","2016-8-3");
insert into books (bname,image,author,descp,price,publisher,publishTime) values
("从你的全世界路过","/images/pic3.jpg","张嘉佳","赠送从你的全世界路过
主题手绘明信片，送给重要的人，或者让我留在你身边！",48.8,"江苏文艺出版社","2016-9-1");
insert into books(bname,image,author,descp,price,publisher,publishTime) 
 values("喧嚣与骚动","/images/pic4.jpg","福克纳","这部小说描写的是19世纪末至
 20世纪20年代美国南方杰弗逊镇的望族康普生家庭的没落以及各个家庭成员的遭遇与精神状态。
 全书共分四个部分",60.8,"湖南文艺出版社","2015-6-10");
truncate table books;
select *from books;