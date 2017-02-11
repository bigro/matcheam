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

## jooqのコードジェネレーター

- flyway起動
`./gradlew flywayMigrate -i`

- jooqのコードジェネレーターコマンド
`./gradlew generateMatcheamJooqSchemaSource`
