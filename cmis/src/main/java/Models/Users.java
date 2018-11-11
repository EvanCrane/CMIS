package Models;

public class Users {

    private int userId;
    private String userName;
    private String encryptedPassword;
    private String organization;
    private int accessLvl;


//    public Users{
//
//    }

    public Users(int userId, String userName, String encryptedPassword){
        this.userId = userId;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;

    }

    public Users(int userId, String userName, String encryptedPassword, String organization, int accessLvl)
    {
        setUserId(userId);
        setUserName(userName);
        setEncryptedPassword(encryptedPassword);
        setOrganization(organization);
        setAccessLvl(accessLvl);
    }
    public Users(int userId, String userName, String encryptedPassword, String organization)
    {
        setUserId(userId);
        setUserName(userName);
        setEncryptedPassword(encryptedPassword);
        setOrganization(organization);

    }



    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEncryptedPassword(){
        return encryptedPassword;
    }
    public void setEncryptedPassword(String encryptedPassword){
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public String toString(){
        return this.userName + "/" + this.encryptedPassword;
    }


    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getAccessLvl() {
        return accessLvl;
    }

    public void setAccessLvl(int accessLvl) {
        this.accessLvl = accessLvl;
    }
}
