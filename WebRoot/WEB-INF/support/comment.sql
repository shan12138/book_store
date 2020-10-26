create table comments (
bid int not null,
uid int not null,
comments varchar(600),
commentTime timestamp default current_timestamp,
foreign key(uid)  references users(uid),
foreign key(bid)  references books(bid),
primary key (bid,uid,commentTime)
);
insert into comments(bid,uid,comments) values
(1,1,"�������ȫ����·������΢�������д���µ����żμѵ�һ����ƪС˵�������н����˷�����������ߵĺܶమ����£�������������ã��а������õ���ʹ��������������ź�����һ�ٴ����������Ҳ������֮��Ļع����ů��������ôһЩ˲�䣬������żμѵĹ����￴���Լ���Ҳ������ôһЩ˲�䣬�����Ϊ��Щ���£����뵽ĳ���ˣ�ĳ�ΰ��顣�������ȫ����·������ע�����Ϊ��������������ů���飬��Ϊ�Ȿ�飬�ǹ�����Ĺ��¡�");
insert into comments(bid,uid,comments) values
(1,2,"����֮ǰ����Сʱ�������鶯�Ժ���żμѣ������˹�������й���

�������˵�������ġ�Сʱ������д���Ǹ߸�˧�Ͱ׸����ǵİ����������żμѵġ������ȫ����·�����ｲ�Ĺ��£����Ǹ��ӽ����ҵ�С����İ���������ЩС����������ƹ������ۣ���ʵ�þ�����ߵĸ���Ǻ͹��ܡ�������Щ��ʵ�ġ���ů�ġ�����������������ж��˹��ķ�˿�����żμѵĹ��³�Ϊ�����Ƕȹ�ÿ��ҹ�����ѡ��");
insert into comments(bid,uid,comments) values
(2,1,"��Ҫ�������֪���Ǹ�����

��Ҫ���ˣ���֪���������š�

����ʢ˵����������Ҳ�Ȳ������Ц��û��������˲������ˡ�

��ʵ���������������ˣ����˿�����Ҳδ�ؾ�������

�����ǣ�û�õ�����Զ��ɧ����

��Щ��˵��������ã�����˭��������ˡ� ");
select *from comments;
truncate table comments;
SELECT comments.*,username FROM comments,users WHERE bid = 1 and comments.uid = users.uid
