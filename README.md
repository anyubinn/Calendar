## 일정 관리 앱 과제
### 🚀 주요 기능 소개
#### ✏️ 일정 생성 및 조회
- 사용자는 할 일, 작성자명, 비밀번호, 작성일/수정일을 포함한 일정을 생성할 수 있습니다.
- 일정 목록은 수정일 및 작성자명, 작성자 id, 작성자 이메일로 필터링하여 조회할 수 있으며, 수정일 기준으로 내림차순 정렬하여 반환됩니다.
- 선택한 일정의 상세 정보를 조회할 수 있습니다.

#### ✏️ 일정 수정 및 삭제
- 사용자는 특정 일정을 선택하여 수정하거나 삭제할 수 있습니다. 
- 수정 시, 해당 일정의 작성자만 할 일을 수정할 수 있으며, 수정일은 수정 완료 시점으로 갱신됩니다. 
- 삭제 시, 요청할 때 작성자의 이메일과 비밀번호를 함께 전달하여 해당 일정이 삭제됩니다.

#### ✏️ 작성자 생성
- 사용자는 작성자명, 비밀번호, 이메일을 포함한 작성자 계정을 생성할 수 있습니다.

#### ✏️ 작성자 수정 및 삭제
- 사용자는 작성자 정보를 수정하거나 삭제할 수 있습니다.
- 수정시, 작성자명, 비밀번호, 이메일을 수정할 수 있으며, 수정일은 수정 완료 시점으로 갱신됩니다.
- 삭제시, 요청할 때 작성자의 이메일과 비밀번호를 함께 전달하여 해당 일정이 삭제됩니다.

### 🚀 API 명세서
<img width="1306" alt="Image" src="https://github.com/user-attachments/assets/5d02b668-f244-4c89-bb19-7929d88f3f39" />
<img width="1305" alt="Image" src="https://github.com/user-attachments/assets/d785334f-daef-4890-b5fc-3587dc0d2ec4" />

### 🚀 ERD
![Image](https://github.com/user-attachments/assets/0dff8219-4cd4-4eff-9fd2-abe7a27bcd3e)

### 🚀 프로젝트 구조
```
.  
├── HELP.md
├── README.md
├── build
│   ├── classes
│   │   └── java
│   │       └── main
│   │           └── com
│   │               └── example
│   │                   └── calendar
│   │                       ├── CalendarApplication.class
│   │                       ├── controller
│   │                       │   ├── CalendarController.class
│   │                       │   └── WriterController.class
│   │                       ├── dto
│   │                       │   ├── CalendarRequestDto.class
│   │                       │   ├── CalendarResponseDto.class
│   │                       │   ├── DeleteCalendarRequestDto.class
│   │                       │   ├── DeleteWriterRequestDto.class
│   │                       │   ├── SearchCalendarRequestDto.class
│   │                       │   ├── WriterRequestDto.class
│   │                       │   └── WriterResponseDto.class
│   │                       ├── entity
│   │                       │   ├── Calendar.class
│   │                       │   └── Writer.class
│   │                       ├── repository
│   │                       │   ├── CalendarRepository.class
│   │                       │   ├── CalendarRepositoryImpl$1.class
│   │                       │   ├── CalendarRepositoryImpl.class
│   │                       │   ├── WriterRepository.class
│   │                       │   ├── WriterRepositoryImpl$1.class
│   │                       │   └── WriterRepositoryImpl.class
│   │                       └── service
│   │                           ├── CalendarService.class
│   │                           ├── CalendarServiceImpl.class
│   │                           ├── WriterService.class
│   │                           └── WriterServiceImpl.class
│   ├── generated
│   │   └── sources
│   │       ├── annotationProcessor
│   │       │   └── java
│   │       │       └── main
│   │       └── headers
│   │           └── java
│   │               └── main
│   ├── reports
│   │   └── problems
│   │       └── problems-report.html
│   ├── resources
│   │   └── main
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── tmp
│       └── compileJava
│           ├── compileTransaction
│           │   ├── backup-dir
│           │   └── stash-dir
│           │       ├── CalendarRepository.class.uniqueId1
│           │       ├── CalendarRepositoryImpl$1.class.uniqueId3
│           │       ├── CalendarRepositoryImpl.class.uniqueId0
│           │       └── CalendarServiceImpl.class.uniqueId2
│           └── previous-compilation-data.bin
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── schedule.sql
├── settings.gradle
└── src
    ├── main
    │   ├── README.md
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── calendar
    │   │               ├── CalendarApplication.java
    │   │               ├── controller
    │   │               │   ├── CalendarController.java
    │   │               │   └── WriterController.java
    │   │               ├── dto
    │   │               │   ├── CalendarRequestDto.java
    │   │               │   ├── CalendarResponseDto.java
    │   │               │   ├── DeleteCalendarRequestDto.java
    │   │               │   ├── DeleteWriterRequestDto.java
    │   │               │   ├── SearchCalendarRequestDto.java
    │   │               │   ├── WriterRequestDto.java
    │   │               │   └── WriterResponseDto.java
    │   │               ├── entity
    │   │               │   ├── Calendar.java
    │   │               │   └── Writer.java
    │   │               ├── repository
    │   │               │   ├── CalendarRepository.java
    │   │               │   ├── CalendarRepositoryImpl.java
    │   │               │   ├── WriterRepository.java
    │   │               │   └── WriterRepositoryImpl.java
    │   │               └── service
    │   │                   ├── CalendarService.java
    │   │                   ├── CalendarServiceImpl.java
    │   │                   ├── WriterService.java
    │   │                   └── WriterServiceImpl.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── example
                    └── calendar
                        └── CalendarApplicationTests.java

```