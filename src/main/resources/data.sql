

insert into roles (id , name) VALUES (1 , 'admin');
insert into roles (id , name) VALUES (2 , 'student');
insert into users (id ,active , mobile_number, password,role_id , name ) VALUES (1 ,true , '0567926657' ,'$2a$10$m550MS8fu7jUgpQKZbSzU.I5EPGeKps.8CN4zM8LJxg2ViyJOeIfG', 1 , 'بهاء احمد بيومي');
insert into admin_user(id) VALUES (1);


insert into menue (id , name ,url ) VALUES
  (1 , 'الرئيسية' , '/') ,
  (2 , 'نبذة عن المسابقة' ,'/about'),
  (3 , 'شروط المسابقة' ,'/constraints'),
  (4 , 'كتيب المسابقة' ,'/book'),
  (5 , 'مواد المسابقة' ,'/subjects'),
  (6 , 'جوائز المسابقة' ,'/prizes'),
  (7 , 'أسئلة المسابقة' ,'/test'),
  (8 , 'التصحيح' ,'/correct'),
  (9 , 'مواد المسابقة' ,'/editSubjects'),
  (10 , 'شروط المسابقة' ,'/editConstraint');

insert into roles(id ,name) VALUES (3 , 'anonymous');

insert into role_menue (role_id , menue_id) VALUES
  (1 ,9),
  (1,10),
  (1,8),
  (2,1),
  (2,2),
  (2,3),
  (2,4),
  (2,5),
  (2,6),
  (2,7),
  (3,1),
  (3,2),
  (3,3),
  (3,6);

insert into menue (id , name ,url) VALUES (300 , 'اعداد جوائز المسابقة' ,'/editPrizes'), (301 , 'اعداد النبذة ' ,'/editAbout');

insert into role_menue (role_id , menue_id) VALUES
  (1,301),
  (1,300);
