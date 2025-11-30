package guru.qa.dZhurHomeWork.less21_Diplom;

import guru.qa.dZhurHomeWork.less21_Diplom.pages.MainPagePages;
import org.junit.jupiter.api.Test;

public class TestUiSmokeMainPage extends TestBaseLess21{
    MainPagePages MainPagePages = new MainPagePages();

    @Test
    void UiTestMainPage() {
        MainPagePages.openPage()
                .checkLogo()
                .checkPhone()
                .checkDogCatalog()
                .checkBucket()
                .checkBucketEmpty();
    }
}
