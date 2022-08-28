# 무신사 과제 - 포인트 API 개발
## 목차
- [개발 환경](#개발-환경)
- [빌드 및 실행하기](#빌드-및-실행하기)

---

## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA Ultimate
    - OS: Windows 11
    - GIT
- Server
    - Spring Boot 2.7.3
    - JPA
    - H2
    - Gradle
    - Junit


## 빌드 및 실행하기
### 터미널 환경
- Git과 Java가 설치된 환경에서 진행한다.

```
$ git clone https://github.com/hs-risktaker/musinsa-point-api.git
$ cd point
$ ./gradlew clean build
$ java -jar build/libs/point-0.0.1-SNAPSHOT.jar
```

- 접속 URI: `http://localhost:8080`

- 에러코드 및 메시지

| 에러코드    | 메시지                  |
|---------| --------------------- |
| `E0000` | 시스템 오류가 발생하였습니다.         |
| `E1001` | 포인트 타입이 올바르지 않습니다.        |
| `E1002` | 포인트 잔액 부족으로 인해 포인트 사용이 불가합니다.          |
| `E1003` | 해당 포인트 사용 이력이 존재하지 않습니다. |
