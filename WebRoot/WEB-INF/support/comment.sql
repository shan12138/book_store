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
(1,1,"《从你的全世界路过》是微博上最会写故事的人张嘉佳的一部短篇小说集。书中讲述了发生在我们身边的很多爱情故事，有念念不忘的美好，有爱而不得的疼痛，有生离死别的遗憾，有一再错过的宿命，也有喧嚣之后的回归和温暖。总有那么一些瞬间，你会在张嘉佳的故事里看到自己，也总有那么一些瞬间，你会因为这些故事，而想到某个人，某段爱情。《从你的全世界路过》，注定会成为你今年读过的最温暖的书，因为这本书，是关于你的故事。");
insert into comments(bid,uid,comments) values
(1,2,"懂事之前读《小时代》，情动以后读张嘉佳，最让人共鸣的情感故事

　　如果说郭敬明的《小时代》描写的是高富帅和白富美们的爱情和生活，那张嘉佳的《从你的全世界路过》里讲的故事，则是更接近你我的小人物的爱情和生活。这些小人物嘴贱心善故事曲折，真实得就像身边的哥儿们和闺密。正是这些真实的、温暖的、毫不矫情的正能量感动了广大的粉丝，让张嘉佳的故事成为了他们度过每个夜晚的首选。");
insert into comments(bid,uid,comments) values
(2,1,"总要错过，才知道是个过错。

总要受伤，才知道曾经爱着。

李宗盛说，春风再美也比不上你的笑，没见过你的人不会明了。

其实，除了天下有情人，别人看到了也未必觉得美。

不过是，没得到的永远在骚动。

有些人说不出哪里好，就是谁都替代不了。 ");
select *from comments;
truncate table comments;
SELECT comments.*,username FROM comments,users WHERE bid = 1 and comments.uid = users.uid
