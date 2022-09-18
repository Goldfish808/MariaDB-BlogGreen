

### 다중컬럼, 즉 복합 유니크키로 두 개의 유니크키의 중복을 막음
```sql
create table boardlikes(
    id int primary KEY auto_increment,
    usersId INT,
    boardsId INT,
    createdAt TIMESTAMP
    
);
ALTER TABLE boardlikes ADD UNIQUE (usersId, boardsId);
```


### 페이징 목록 개수 변경할 때
- boards.xml 에 id=paging 부분에 ceil(count(*)/5) totalPage
- boards.xml 에 id=findAll 부분에 FETCH NEXT 5 ROWS ONLY
- BoardsService 에 게시글목록보기() 메서드에 int startNum = page * 5;

### 설정방법
- MyBatisConfig 파일 필요
- resources/mapper/*.xml 파일 필요
- Users 엔티티 필요
- UsersDao 인터페이스 생성 필요

### MariaDB 사용자 생성 및 권한 주기
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```

### 테이블 생성
```sql
USE greendb;

create table users(
    id int primary KEY auto_increment,
    username varchar(20) unique,
    password varchar(20),
    email varchar(50),
    createdAt TIMESTAMP
);
 
create table boards(
    id int primary key,
    title varchar(150),
    content longtext,
    usersId int,
    createdAt TIMESTAMP
);
```

### 더미데이터 추가
```sql
insert into users(username, password, email, createdAt) values('ssar', '1234', 'ssar@nate.com', NOW());
insert into users(username, password, email, createdAt) values('cos', '1234', 'cos@nate.com', NOW());
insert into users(username, password, email, createdAt) values('hong', '1234', 'hong@nate.com', NOW());
COMMIT;
```