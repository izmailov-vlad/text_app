package ru.miracle.madmeditation.presentation.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.data.repository.authorization.AuthorizationRepositoryImpl;
import ru.miracle.madmeditation.data.storage.db.AppDatabase;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.domain.usecase.authorization.LogInUseCase;
import ru.miracle.madmeditation.domain.usecase.authorization.LogInUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.user.GetAllUsersUseCase;
import ru.miracle.madmeditation.domain.usecase.user.GetAllUsersUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.user.GetUserInfoUseCase;
import ru.miracle.madmeditation.domain.usecase.user.GetUserInfoUseCaseImpl;

public class LoginViewModel extends AndroidViewModel {

    // PRIVATE FIELDS
    private final LogInUseCase logInUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public final MutableLiveData<Response<User>> userInfoMutableLiveData = new MutableLiveData<>();

    // PUBLIC FIELDS
    public MutableLiveData<Boolean> loginResponseLiveData = new MutableLiveData<>();

    //PUBLIC METHODS
    public LoginViewModel(@NonNull @NotNull Application application) {
        super(application);
        getUserInfoUseCase = new GetUserInfoUseCaseImpl(new AuthorizationRepositoryImpl(
                AppDatabase.getAppDatabase(application).userDao()
        ));
        logInUseCase = new LogInUseCaseImpl(new AuthorizationRepositoryImpl(
                AppDatabase.getAppDatabase(application).userDao()
        ));
        getAllUsersUseCase = new GetAllUsersUseCaseImpl(new AuthorizationRepositoryImpl(
                AppDatabase.getAppDatabase(application).userDao()
        ));
    }


    public void getUser() {
        compositeDisposable.add(getUserInfoUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<User>() {
                   @Override
                   public void onSuccess(@NotNull User user) {
                       userInfoMutableLiveData.postValue(Response.success(user));
                   }

                   @Override
                   public void onError(@NotNull Throwable e) {

                       userInfoMutableLiveData.postValue(Response.success(null));
                       Logger.error(e);
                   }
               }
            )
        );
    }
    public void getAllUsers() {
        compositeDisposable.add(getAllUsersUseCase.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(@NotNull List<User> users) {
                        for(int i =0; i < users.size(); i++) {
                            Logger.log("User uid", String.valueOf(users.get(i).uid));
                            Logger.log("User login", users.get(i).login);
                            Logger.log("User password", users.get(i).password);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Logger.error(e);

                    }
                })
        );

    }

    public void login(String login, String password) {
        if(login.equals("") || password.equals("") || !login.contains("@")) {
            loginResponseLiveData.setValue(false);
            return;
        }

        compositeDisposable.add(logInUseCase.logIn(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<User>() {

                    @Override
                    public void onSuccess(@NotNull User user) {
                        Logger.log("logIn", "success");
                        loginResponseLiveData.postValue(true);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Logger.log("logIn", "error");
                        loginResponseLiveData.postValue(false);
                    }
                })
        );
    }
}
