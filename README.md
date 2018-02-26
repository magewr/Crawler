# 서비스 클라이언트 스켈레톤 아키텍쳐

현재 지속적으로 작업중인 프로젝트입니다. (Android Studio 3.0)

- DI(Dagger2)를 이용한 MVP, The Clean Architecture를 Base로 함.
![screensh](https://images.contentful.com/emmiduwd41v7/6HuRyfP1Vm4UEIiIEIeMwS/5f66cdc5c4a5af60f24920548babace1/clean-android-architecture2.jpg)

- Remote Repository - Retrofit2+OkHttp3+Gson 사용

- Local Repository - Realm 사용 (Sample UI에는 제외)

- RxJava2 사용

- Model+Presentation에서 Mockito 사용한 JUnitTest 코드 작성 (Sample UI에는 제외)

- View에서 Espresso를 이용한 Android Instrumentation Test 코드 작성 (Sample UI에는 제외)




# Sample UI

샘플 API는 http://doc.coinone.co.kr/#api-Public-Ticker 를 사용하였습니다.

샘플 앱으로 위에서 제공하는 API로 비트코인의 현재 가격을 5초마다 갱신하여 보여줍니다.

View의 오픈소스로 Glide, TickerView 사용하였습니다.

Glide : https://github.com/bumptech/glide

TickerView : https://github.com/robinhood/ticker

Splash image 출처 : https://dribbble.com/shots/1433198-Zuck-Dawg-making-it-rain


# 활용한 앱

https://github.com/magewr/dac_story
