package un.app1.pageModule.appHome;

public interface HomeView {

    void setBalance(String balance);

    void setLastLogin(String lastLogin);

    void setLogin(String login);

    void setUserName(String username);

    void setShowUserImage();

    void setUserImage(String imageUrl);

    void setLayoutChecking();

    void setLayoutSignOut();

    void setLayoutSignIn();

    void setLayoutBlank();

    void logOut();

    void goToMenuActivity(int position);

}
