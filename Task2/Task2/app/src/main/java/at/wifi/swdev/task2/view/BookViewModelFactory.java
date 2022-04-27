package at.wifi.swdev.task2.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BookViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;

    public BookViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass == BookViewModel.class) {
            return (T) new BookViewModel(application);
        }

        return super.create(modelClass);
    }

}
