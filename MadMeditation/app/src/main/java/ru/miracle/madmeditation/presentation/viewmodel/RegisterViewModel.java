package ru.miracle.madmeditation.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.data.repository.authorization.AuthorizationRepositoryImpl;
import ru.miracle.madmeditation.data.storage.db.AppDatabase;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.domain.usecase.authorization.LogInUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.register.RegisterUserUseCase;
import ru.miracle.madmeditation.domain.usecase.register.RegisterUserUseCaseImpl;

public class RegisterViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> registerMutableLiveData = new MutableLiveData<>();
    public LiveData<Boolean> registerLiveData = registerMutableLiveData;

    private final RegisterUserUseCase registerUserUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final DisposableCompletableObserver registerObsesrver = new DisposableCompletableObserver() {
        @Override
        public void onComplete() {
            Logger.log("registration", "success");
            registerMutableLiveData.postValue(true);
        }

        @Override
        public void onError(@NotNull Throwable e) {
            registerMutableLiveData.postValue(false);
            Logger.error(e);
        }

    };

    public RegisterViewModel(@NonNull @NotNull Application application) {
        super(application);
        registerUserUseCase = new RegisterUserUseCaseImpl(new AuthorizationRepositoryImpl(
                AppDatabase.getAppDatabase(application).userDao()
        ));

    }

    public void register(String login, String password) {
        compositeDisposable.add(registerUserUseCase.register(login, password)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(registerObsesrver));
    }
}
