/*
create table META_CATEGORY_1
(
  ID    INTEGER,
  DNAME VARCHAR(100)
);

create table META_CATEGORY_2
(
  PID   INTEGER,
  ID    INTEGER,
  DNAME VARCHAR(100)
);

create table META_CATE_CLASSIFICATION
(
  ID        INTEGER not null,
  CATEGORY1 INTEGER,
  CATEGORY2 INTEGER,
  CATEGORY3 INTEGER,
  ONTOURI   VARCHAR(200),
  ONTONAME  VARCHAR(100)
);

create table Category_ID_Table
(
  ID INTEGER not null
);

alter table META_CATE_CLASSIFICATION
  add primary key (ID)
  using index 

*/

insert into META_CATEGORY_1 (ID, DNAME)
values (427, '����ר��');
insert into META_CATEGORY_1 (ID, DNAME)
values (428, 'ͻ������');
insert into META_CATEGORY_1 (ID, DNAME)
values (429, '��ר��');
insert into META_CATEGORY_1 (ID, DNAME)
values (430, '��Ⱦ��ר��');
insert into META_CATEGORY_1 (ID, DNAME)
values (431, '�ڿƲ�֤');
insert into META_CATEGORY_1 (ID, DNAME)
values (432, '����');
insert into META_CATEGORY_1 (ID, DNAME)
values (433, '���Ʋ�֤');
insert into META_CATEGORY_1 (ID, DNAME)
values (434, '���˿Ʋ�֤');
insert into META_CATEGORY_1 (ID, DNAME)
values (435, '������ݿ�');
insert into META_CATEGORY_1 (ID, DNAME)
values (436, '���Ʋ�֤');
insert into META_CATEGORY_1 (ID, DNAME)
values (437, '��ǻ�Ʋ�֤');
insert into META_CATEGORY_1 (ID, DNAME)
values (438, '��ٿƲ�֤');
insert into META_CATEGORY_1 (ID, DNAME)
values (439, '��ҽ֤��');
insert into META_CATEGORY_1 (ID, DNAME)
values (440, '��Ⱦ��');
commit;

insert into META_CATEGORY_2 (PID, ID, DNAME)
values (140, 159, '������ר��1');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (140, 160, '������ר��2');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (427, 441, '����ר��');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (428, 478, 'ͻ������');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (429, 444, '��ר��');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (430, 445, '�����Ը���');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 446, '����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 447, '����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 448, '�����Գմ�');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 449, '�з�');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 450, '��Ѫѹ');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 451, '���Ĳ�');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 452, '����ϵͳ����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 453, '��Ѫ�ܼ���');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 454, '��ϵͳ����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 455, '����ϵͳ');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (432, 456, '����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (433, 457, '����������');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (433, 458, 'Ӥ������');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (433, 459, 'ѧ��ǰ��ͯ����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (433, 460, '��ͯ����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (434, 461, '�ɹ�ͷ����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (434, 462, '��׵��');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (435, 463, '���');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (434, 464, '�ǿƼ���');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (436, 465, '�¾���');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (436, 466, '�������ۺ�֤');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (436, 467, '����֢');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (436, 468, '��������');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (436, 469, '���Ƽ���');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (437, 470, '��ǻ����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (438, 471, '�ۿƼ���');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (439, 472, '��ҽ֤��');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 473, '������');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (440, 474, '���̲�');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (431, 475, '����ϵͳ����');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (434, 476, '��������');
insert into META_CATEGORY_2 (PID, ID, DNAME)
values (140, 225, '������ר��3');
commit;

insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (477, 427, 441, null, 'http://dart.zju.edu.cn/dartcore/dart#����ר��', '����ר��');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (479, 428, 478, null, 'http://dart.zju.edu.cn/dartcore/dart#ͻ������ר��', 'ͻ������ר��');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (480, 429, 444, null, 'http://dart.zju.edu.cn/dartcore/dart#��ר��', '��ר��');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (481, 430, 445, null, 'http://dart.zju.edu.cn/dartcore/dart#�����Ը���', '�����Ը���');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (482, 431, 446, null, 'http://dart.zju.edu.cn/dartcore/dart#����', '����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (483, 431, 447, null, 'http://dart.zju.edu.cn/dartcore/dart#����', '����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (484, 431, 448, null, 'http://dart.zju.edu.cn/dartcore/dart#�����Գմ�', '�����Գմ�');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (485, 431, 449, null, 'http://dart.zju.edu.cn/dartcore/dart#�з�', '�з�');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (486, 431, 450, null, 'http://dart.zju.edu.cn/dartcore/dart#��Ѫѹ', '��Ѫѹ');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (487, 431, 451, null, 'http://dart.zju.edu.cn/dartcore/dart#���Ĳ�', '���Ĳ�');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (488, 431, 452, null, 'http://dart.zju.edu.cn/dartcore/dart#����ϵͳ', '����ϵͳ');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (489, 431, 453, null, 'http://dart.zju.edu.cn/dartcore/dart#��Ѫ�ܼ���', '��Ѫ�ܼ���');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (490, 431, 454, null, 'http://dart.zju.edu.cn/dartcore/dart#��ϵͳ����', '��ϵͳ����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (491, 431, 455, null, 'http://dart.zju.edu.cn/dartcore/dart#����ϵͳ', '����ϵͳ');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (492, 431, 473, null, 'http://dart.zju.edu.cn/dartcore/dart#������', '������');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (493, 431, 475, null, 'http://dart.zju.edu.cn/dartcore/dart#����ϵͳ����', '����ϵͳ����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (494, 432, 456, null, 'http://dart.zju.edu.cn/dartcore/dart#����', '����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (495, 433, 457, null, 'http://dart.zju.edu.cn/dartcore/dart#��ͯ����', '��ͯ����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (496, 433, 458, null, 'http://dart.zju.edu.cn/dartcore/dart#Ӥ������', 'Ӥ������');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (497, 433, 459, null, 'http://dart.zju.edu.cn/dartcore/dart#ѧ��ǰ��ͯ����', 'ѧ��ǰ��ͯ����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (498, 433, 460, null, 'http://dart.zju.edu.cn/dartcore/dart#��ͯ����', '��ͯ����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (499, 434, 461, null, 'http://dart.zju.edu.cn/dartcore/dart#�ɹ�ͷ����', '�ɹ�ͷ����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (500, 434, 462, null, 'http://dart.zju.edu.cn/dartcore/dart#��׵��', '��׵��');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (501, 434, 464, null, 'http://dart.zju.edu.cn/dartcore/dart#�ǿƼ���', '�ǿƼ���');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (502, 434, 476, null, 'http://dart.zju.edu.cn/dartcore/dart#��������', '��������');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (503, 435, 463, null, 'http://dart.zju.edu.cn/dartcore/dart#����Ʒ�', '����Ʒ�');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (504, 436, 465, null, 'http://dart.zju.edu.cn/dartcore/dart#�¾���', '�¾���');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (505, 436, 466, null, 'http://dart.zju.edu.cn/dartcore/dart#�������ۺ�֢', '�������ۺ�֢');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (506, 436, 467, null, 'http://dart.zju.edu.cn/dartcore/dart#����֢', '����֢');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (507, 436, 468, null, 'http://dart.zju.edu.cn/dartcore/dart#��������', '��������');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (508, 436, 469, null, 'http://dart.zju.edu.cn/dartcore/dart#���Ƽ���', '���Ƽ���');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (509, 437, 470, null, 'http://dart.zju.edu.cn/dartcore/dart#��ǻ����', '��ǻ����');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (510, 438, 471, null, 'http://dart.zju.edu.cn/dartcore/dart#�ۿƼ���', '�ۿƼ���');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (511, 439, 472, null, 'http://dart.zju.edu.cn/dartcore/dart#��ҽ��֤', '��ҽ��֤');
insert into META_CATE_CLASSIFICATION (ID, CATEGORY1, CATEGORY2, CATEGORY3, ONTOURI, ONTONAME)
values (512, 440, 474, null, 'http://dart.zju.edu.cn/dartcore/dart#���̲�', '���̲�');
commit;



