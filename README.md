# matcheam

## pullした後にeclipseプロジェクトにする

`gradlew eclipse`

Eclipseにインポート

File->Import->General->Exisiring Project

## Spring Boot のブート

`./gradlew bootRun`

## MySQLの設定

インストール直後は

```
$ mysql.server start
```
でMySQL起動し、

+ ユーザー：root  
+ パスワード：なし  

でログインできます。ログインコマンドはこれ。

```
$ mysql -uroot
```
その後下記のユーザを作成します。

+ ユーザー：matcheam@localhost
+ パスワード：pass

コマンドはこれ。
```
create user matcheam@localhost identified by 'pass';
```

databaseを作って、権限付与

```
create database matcheam;
grant all on *.* to matcheam@localhost; 
```


## jooqのコードジェネレーター

- flyway起動
`./gradlew flywayMigrate -i`

- jooqのコードジェネレーターコマンド
`./gradlew generateMatcheamJooqSchemaSource`

## タスク管理

https://waffle.io/bigro/matcheam

## waffle.ioの使い方

- https://blog.yohei.org/github-kanban-waffle-io/
- https://github.com/waffleio/waffle.io
