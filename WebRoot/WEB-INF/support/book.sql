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
("�����ȫ����·��","/images/pic1.jpg","�żμ�","6 ����,����200��ᣬ
���ὲ���µ����żμѣ��׸�����Ķ�����",50,"�������ճ�����","2016-7-3");
insert into books (bname,image,author,descp,price,publisher,publishTime) values
("�����ȫ����·��","/images/pic2.jpg","�żμ�","��������Ƥ��װ����ѡ
����2014�й������80����Ʒ��ʮ�껪�ﳩ��С˵������9�����£�����10����Ӱ",38.8,"��е���ճ�����","2016-8-3");
insert into books (bname,image,author,descp,price,publisher,publishTime) values
("�����ȫ����·��","/images/pic3.jpg","�żμ�","���ʹ����ȫ����·��
�����ֻ�����Ƭ���͸���Ҫ���ˣ�����������������ߣ�",48.8,"�������ճ�����","2016-9-1");
insert into books(bname,image,author,descp,price,publisher,publishTime) 
 values("������ɧ��","/images/pic4.jpg","������","�ⲿС˵��д����19����ĩ��
 20����20��������Ϸ��ܸ�ѷ������念������ͥ��û���Լ�������ͥ��Ա�������뾫��״̬��
 ȫ�鹲���ĸ�����",60.8,"�������ճ�����","2015-6-10");
truncate table books;
select *from books;