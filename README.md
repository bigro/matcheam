# matcheam

## pullした後にeclipseプロジェクトにする

`gradlew eclipse`

Eclipseにインポート

File->Import->General->Exisiring Project

## Spring Boot のブート

`./gradlew bootRun`

## MySQLの設定

ユーザー：root  
パスワード：なし  
スキーマ：matcheam

```
grant all on *.* to matcheam@localhost; 
create database matcheam;
```
ユーザー：matcheam@localhost  
パスワード：pass


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
