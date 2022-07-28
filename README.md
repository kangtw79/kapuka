## API
### 장소 검색

* /v1/place/search?keyword={keyword}


### 키워드 Top N

* /v1/keyword/top-ranks?limit={limit}


### Dependencies

* Gradle : build script
* Spring boot (web , Jpa) : 스프링 프레임웍
* Spring management : 스프링 라이브러리 버전 관리
* Spring cloud ( histirx , openfeign) : feign client, fallback
* lombok : annotation processor

### TO-DO
* 검색어 비교 전략 개선
* DDD 아키텍처로 리팩토링
* 키워드 Top 조회 시 CQRS 에서 Query 로직 구현 (Projection)
* 테스트 커버리지 100%
* 예외 처리

### Integration Test

* /api-test.http
