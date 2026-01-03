package ge.tbc.testautomation.tests;
import com.codeborne.selenide.*;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.CreateListSteps;
import ge.tbc.testautomation.steps.ListPageSteps;
import ge.tbc.testautomation.steps.SignInSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
@Test(groups = {"E2E - Creating and saving a list  - SCRUM-T27"})
public class CreatingAndSavingList{
    SignInSteps signInSteps;
    ListPageSteps listPageSteps;
    CreateListSteps createListSteps;
    @BeforeClass
    public void setUp(){
        signInSteps =new SignInSteps();
        listPageSteps = new ListPageSteps();
        createListSteps =new CreateListSteps();

        Configuration.browser = "Edge";
        Configuration.timeout = 8000;
        open("https://letterboxd.com/");
    }
    @Test(description = "signing in", priority = 1)
    public void signingIn(){
        signInSteps.goToSignInSection()
                .fillUserNameInput()
                .fillPasswordInput()
                .clickSignInButton()
                .assertHomepageMove();
  }
    @Test(description = "navigating to List page", priority = 2)
    public void navigatingToListPage(){
        listPageSteps.navigateToListPage()
                .assertListPageTitle()
                .assertListPageUrl();
    }
    @Test(description = "starting new list ", priority = 3)
    public void startingNewList(){
          listPageSteps.creatingNewList();
          createListSteps.assertNewListPageTitle();
    }
    @Test(description = "filling list details ", priority = 4)
    public void fillingListDetails(){
        createListSteps.fillListName()
                .selectSharingPolicy()
                .checkRankedOption()
                .addMovie(Constants.MOVIE_1)
                .addMovie(Constants.MOVIE_2)
                .fillDescription()
                .assertListNameValue()
                .assertRankedCheckboxChecked()
                .assertSharingPolicy()
                .assertMovieItems(2,new String[]{Constants.MOVIE_1, Constants.MOVIE_2});
    }
    @Test(description = "saving list", priority = 5)
    public void savingList(){
        createListSteps.saveList()
                .assertSuccessNotification()
                .assertSaveButtonDisabled();
    }
}
