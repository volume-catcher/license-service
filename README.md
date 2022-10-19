# ©️ License-Service

## 📌 프로젝트 환경

- Build: Gradle 7.4.1
- Language: Java 11
- Spring Boot 2.7.1
- MariaDB 10.8.3
- Packaging: Jar

## 📌 기술 스택

- JPA
- QueryDSL 5.0.0
- JWT
- React 18.2.0

## 📌 요구 사항

- 라이선스 요구 사항
  - 라이선스의 정규식 `([A-Z\d]{4}\-){3}[A-Z\d]{4}`
    - ex) `AJW3-18EH-3MSF-391V`, 숫자와 알파벳 대문자로 구성된 19자의 문자
    - `\d` - 숫자와 매치, `[0-9]`와 동일
  - 한 라이선스는 여러 종류의 제품을 인증할 수 있다
    - 예를 들어 MS office 라이선스를 구매했을 때 해당 라이선스로 Excel, PowerPoint, Word 등 여러 종류의 제품을 인증할 수 있다
  - 한 라이선스로 각 제품별 인증 가능한 기기 수는 한정되어 있다
    - 위의 예시 상황에서 Excel은 기기 2대, PowerPoint는 기기 1대, Word 3대 이런 식으로 지정할 수 있다
  - 라이선스는 유효 기간이 존재한다
  - 라이선스는 발급 일시, 수정 일시 정보가 있다
  - 특정 라이선스의 제품을 모두 삭제해도 라이선스는 삭제되지 않는다
- 제품 요구 사항
  - 제품은 발급 일시, 수정 일시 정보가 있다
- 웹 서비스 요구 사항
  - 웹 서비스는 관리자가 라이선스를 총체적으로 관리하기 위한 페이지다
  - 관리자 권한으로 라이선스 모델을 CRUD 한다

## 📌 다이어그램

### Use Case Diagram

<img src="https://user-images.githubusercontent.com/54898819/179160850-b43e6663-63b2-499b-9171-00664c2e822e.jpg"  height="500"/>

- 사용자
  - 사용자는 라이선스의 유효성을 검증받는다
  - 사용자는 라이선스의 남은 인증 횟수/유효 기간을 확인할 수 있다
- 관리자
  - 관리자는 라이선스를 생성할 수 있다
  - 관리자는 제품을 생성/삭제할 수 있다
  - 관리자는 라이선스에 제품을 생성/삭제할 수 있다
  - 관리자는 인증 정보를 확인할 수 있다
  - 관리자는 라이선스 관리, 인증 정보 관리, 제품 관리를 위해 관리자 인증을 해야 한다

### ERD

<img width="671" alt="image" src="https://user-images.githubusercontent.com/54898819/196646946-ed693393-74dc-4ba9-a84e-e258f482dd55.png" width="600"/>

[erdcloud 바로가기](https://www.erdcloud.com/d/toKwdCHwxgjKB4B7p)
