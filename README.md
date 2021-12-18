## 설명
- 해당 코드는 jxls 라는 엑셀 라이브러리를 이용하여 작성된 예제코드입니다. 이 예제코드에서는 다음과 같은 기능을 다룹니다.
  - 엑셀 업로드
  - 엑셀 다운로드
  
<br>

## 프로젝트 환경
- 프로젝트 환경구성은 다음과 같습니다.
  - JAVA 11
  - Spring Boot 2.6.1
  - Thymeleaf
  
<br>

## 테스트 하는법
- 메인화면 접속
  - 애플리케이션을 실행하시고 http://localhost:8080/study 로 접속하시면 아래사진의 페이지가 보여집니다.
  ![image](https://user-images.githubusercontent.com/90641957/146635040-3caa7526-52be-4691-978a-1dde1db950b0.png)

- 엑셀 다운로드
  - excel download 버튼을 클릭하시면 위 목록의 내용을 엑셀파일로 다운로드 되어집니다.
  
- 엑셀 업로드
  - sample file donwload를 클릭하시면 샘플양식의 엑셀파일이 다운로드 됩니다. 내용을 채워넣으신 후 excel upload 버튼을 누르시면 됩니다.
    StudyController의 excelUpload 메서드를 보시면 엑셀업로드 후 업로드된 데이터를 출력하게 됩니다.
  
<br>

## 참고
- 해당예제와 관련하여 참고할 수 있는 링크를 첨부합니다.
  - <a href='https://kim-jong-hyun.tistory.com/25'>jxls로 간편하게 엑셀업로드 구현하기</a>
  - <a href='https://kim-jong-hyun.tistory.com/24'>jxls로 간편하게 엑셀다운로드 구현하기</a>
  - <a href='http://jxls.sourceforge.net'>jxls 공식문서</a>
- 참고로 위 링크에 정리되있는 예제코드와 해당 레포지토리에 있는 예제코드는 약간 다르지만 크게 차이는 없고 pom.xml에 명시된 jxls-reader 버전이 다릅니다.
  jxls-reader 2.0.5 에서 excel upload가 정상적으로 되었었는데 추후 확인해보니 되지않았던 이슈가 발생되어 2.0.6으로 올리니 정상적으로 되었습니다.
 
