# ©️ License-Service

## 📌 결과물

<img alt="result" src="https://user-images.githubusercontent.com/54898819/201735192-1c9cc060-f579-42a9-a46c-071b1a6fadbc.gif" width="500"/>

## 📌 프로젝트 설명

본 프로그램은 관리자가 라이선스를 관리하기 위한 프로그램이다.\
라이선스, 제품을 생성하고 라이선스에 제품을 등록할 수 있다.

## 📌 프로젝트 환경

- Build: Gradle 7.4.1
- Language: Java 11
- Spring Boot 2.7.1
- MariaDB 10.8.3
- Packaging: Jar
- React 18.2.0
- JPA
- QueryDSL

## 📌 기능

- 고객 서비스 (auth)
  - 사용자는 라이선스의 유효성을 검증받는다
  - 사용자는 라이선스의 남은 인증 횟수/유효 기간을 확인할 수 있다
- 관리자 서비스 (license-service)
  - 관리자는 라이선스를 생성할 수 있다
  - 관리자는 제품을 생성/삭제할 수 있다
  - 관리자는 라이선스에 제품을 생성/삭제할 수 있다
  - 관리자는 인증 정보를 확인할 수 있다
  - 관리자는 라이선스 관리, 인증 정보 관리, 제품 관리를 위해 관리자 인증을 해야 한다

## 📌 요구 사항

- 라이선스 요구 사항
  - 라이선스의 정규식 `([A-Z\d]{4}\-){3}[A-Z\d]{4}`
    - ex) `AJW3-18EH-3MSF-391V`, 숫자와 알파벳 대문자로 구성된 19자의 문자
    - `\d` - 숫자와 매치, `[0-9]`와 동일
  - 한 라이선스는 여러 종류의 제품을 인증할 수 있다
    - 예를 들어 MS office 라이선스를 구매했을 때 해당 라이선스로 Excel, PowerPoint, Word 등 여러 종류의 제품을 인증할 수 있다
  - 한 라이선스로 각 제품별 인증 가능한 기기 수는 한정되어 있다
    - 위의 예시 상황에서 Excel은 기기 2대, PowerPoint는 기기 1대, Word 3대와 같은 방식으로 지정할 수 있다
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

<img alt="use case diagram" src="https://user-images.githubusercontent.com/54898819/179160850-b43e6663-63b2-499b-9171-00664c2e822e.jpg" height="500"/>

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

<img alt="erd" src="https://user-images.githubusercontent.com/54898819/201732705-24699154-4e1a-48ae-ac69-fbddcfd6bd06.png" width="600"/>

[erdcloud 바로가기](https://www.erdcloud.com/d/toKwdCHwxgjKB4B7p)

### Sequence Diagram (고객 서비스 - 인증)

<img alt="sequence diagram" src="https://user-images.githubusercontent.com/54898819/201733373-60434848-8ef5-4ba1-8668-7030605223c7.png" height="600"/>

[GitMind 바로가기](https://gitmind.com/app/flowchart/be311975818)

## 📌 API 명세

### 고객 서비스 (auth)

| INDEX | Method | URL                                                  | Parameter                          | Description    |
| ----- | ------ | ---------------------------------------------------- | ---------------------------------- | -------------- |
| 1     | POST   | /api/v1/auths                                        | 라이선스키, 제품이름, 기기일련번호 | 인증 토큰 받기 |
| 2     | GET    | /api/v1/licenses/{licenseKey}/products/{productName} | 라이선스키, 제품이름               | 계약 정보 받기 |

### 관리자 서비스 (license-service)

| INDEX | Method | URL                                     | Parameter                                                       | Description               |
| ----- | ------ | --------------------------------------- | --------------------------------------------------------------- | ------------------------- |
| 1     | POST   | /api/v1/signup                          | ID, PWD                                                         | 회원가입                  |
| 2     | POST   | /api/v1/signin                          | ID, PWD                                                         | 로그인                    |
| 3     | POST   | /api/v1/licenses                        | -                                                               | 라이선스 발급             |
| 4     | GET    | /api/v1/licenses                        | -                                                               | 모든 라이선스 페이징 조회 |
| 5     | GET    | /api/v1/licenses/{searchWord}           | -                                                               | 라이선스키 검색           |
| 6     | GET    | /api/v1/licenses/{licenseKey}/contracts | -                                                               | 특정 라이선스의 계약 조회 |
| 7     | POST   | /api/v1/products                        | 제품이름                                                        | 제품 생성                 |
| 8     | GET    | /api/v1/products                        | -                                                               | 모든 제품 페이징 조회     |
| 9     | GET    | /api/v1/products/{searchWord}           | -                                                               | 제품이름 검색             |
| 10    | GET    | /api/v1/products/names                  | -                                                               | 모든 제품 조회            |
| 11    | POST   | /api/v1/contracts                       | 라이선스키, 제품이름, 활성여부, 제품별 인증 가능 횟수, 만료일시 | 계약 생성                 |
| 12    | PUT    | /api/v1/contracts                       | 라이선스키, 제품이름, 활성여부, 제품별 인증 가능 횟수, 만료일시 | 계약 정보 업데이트        |
| 13    | PATCH  | /api/v1/contracts                       | 라이선스키, 제품명, 활성여부                                    | 계약 활성여부 업데이트    |
| 14    | GET    | /api/v1/contracts                       | -                                                               | 모든 계약 조회            |
