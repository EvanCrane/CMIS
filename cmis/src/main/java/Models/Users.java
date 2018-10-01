package Models;

public class Users {

    private int userId;
    private String userName;
    private String encryptedPassword;


//    public Users{
//
//    }

    public Users(int userId, String userName, String encryptedPassword){
        this.userId = userId;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;

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


}
