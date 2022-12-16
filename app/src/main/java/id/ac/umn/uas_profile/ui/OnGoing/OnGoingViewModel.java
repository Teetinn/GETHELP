package id.ac.umn.uas_profile.ui.OnGoing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OnGoingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OnGoingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favorites fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}