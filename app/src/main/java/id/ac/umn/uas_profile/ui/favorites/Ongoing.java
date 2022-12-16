package id.ac.umn.uas_profile.ui.favorites;

public class Ongoing {

    String name;
    int imageView;

    public Ongoing(String name, int imageView) {
        this.name = name;
        this.imageView = imageView;
    }


    public String getName(){
        return name;
    }

    public int getImageView(){
        return imageView;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImageView(int imageView){
        this.imageView = imageView;
    }
}
