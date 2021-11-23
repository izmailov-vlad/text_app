package ru.miracle.madmeditation.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import ru.miracle.madmeditation.data.models.CatResponse;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.models.QuotesResponse;
import ru.miracle.madmeditation.data.models.comparator.FeelingsComparator;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.data.repository.authorization.AuthorizationRepositoryImpl;
import ru.miracle.madmeditation.data.repository.profile.ProfileRepositoryImpl;
import ru.miracle.madmeditation.data.storage.db.AppDatabase;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.domain.entities.FeelingsDto;
import ru.miracle.madmeditation.domain.entities.PhotoDto;
import ru.miracle.madmeditation.domain.usecase.cat.GetCatUseCase;
import ru.miracle.madmeditation.domain.usecase.cat.GetCatUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.feelings.GetFeelingsUseCase;
import ru.miracle.madmeditation.domain.usecase.feelings.GetFeelingsUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.photos.DeletePhotoUseCase;
import ru.miracle.madmeditation.domain.usecase.photos.DeletePhotoUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.photos.GetAllPhotosUseCase;
import ru.miracle.madmeditation.domain.usecase.photos.GetAllPhotosUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.photos.InsertPhotoUseCase;
import ru.miracle.madmeditation.domain.usecase.photos.InsertPhotoUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.quotes.GetQuotesUseCase;
import ru.miracle.madmeditation.domain.usecase.quotes.GetQuotesUseCaseImpl;
import ru.miracle.madmeditation.domain.usecase.user.GetUserInfoUseCase;
import ru.miracle.madmeditation.domain.usecase.user.GetUserInfoUseCaseImpl;

public class DashboardViewModel extends AndroidViewModel {

    private final GetFeelingsUseCase getFeelingsUseCase;
    private final GetQuotesUseCase getQuotesUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final GetAllPhotosUseCase getAllPhotosUseCase;
    private final InsertPhotoUseCase insertPhotoUseCase;
    private final DeletePhotoUseCase deletePhotoUseCase;
//    private final GetCatUseCase getCatUseCase;

    public final MutableLiveData<Response<FeelingsResponse>> feelingsResponseMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<Response<QuotesResponse>> quotesResponseMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<Response<User>> userInfoMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<Response<CatResponse>> catResponseMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<Response<List<Photo>>> photosResponseMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<Response<Boolean>> insertPhotosResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Response<Boolean>> deletePhotoMutableLiveData = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<Response<FeelingsResponse>> getFeelingsResponseLiveData() {
        return feelingsResponseMutableLiveData;
    }

    public LiveData<Response<QuotesResponse>> getQuotesResponseLiveData() {
        return quotesResponseMutableLiveData;
    }

    public LiveData<Response<User>> getUserInfoLiveData() {
        return userInfoMutableLiveData;
    }

    public LiveData<Response<CatResponse>> getCatResponseLiveData() {
        return catResponseMutableLiveData;
    }



    public DashboardViewModel(@NonNull @NotNull Application application) {
        super(application);
        getFeelingsUseCase = new GetFeelingsUseCaseImpl();
        getQuotesUseCase = new GetQuotesUseCaseImpl();
        getUserInfoUseCase = new GetUserInfoUseCaseImpl(new AuthorizationRepositoryImpl(
                AppDatabase.getAppDatabase(application).userDao()
        ));
        deletePhotoUseCase = new DeletePhotoUseCaseImpl(new ProfileRepositoryImpl(
                AppDatabase.getAppDatabase(application).photoDao())
        );
        getAllPhotosUseCase = new GetAllPhotosUseCaseImpl(
                new ProfileRepositoryImpl (
                        AppDatabase.getAppDatabase(application).photoDao()
                )
        );
        insertPhotoUseCase = new InsertPhotoUseCaseImpl(
                new ProfileRepositoryImpl(
                        AppDatabase.getAppDatabase(application).photoDao()
                )
        );
//        getCatUseCase = new GetCatUseCaseImpl(new ProfileRepositoryImpl());
    }

    public void getFeelings() {
        compositeDisposable.add(getFeelingsUseCase.getFeelings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                        new DisposableSingleObserver<FeelingsResponse>() {
                            @Override
                            public void onSuccess(@NotNull FeelingsResponse feelingsResponse) {
                                Logger.log("TAG", feelingsResponse.data.toString());
                                Collections.sort(feelingsResponse.data, new FeelingsComparator());
                                feelingsResponseMutableLiveData.setValue(Response.success(feelingsResponse));

                            }

                            @Override
                            public void onError(@NotNull Throwable exception) {
                                Logger.error(exception);
                            }
                        }
                )
        );
    }

    public void getQuotes() {
        compositeDisposable.add(getQuotesUseCase.getQuotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<QuotesResponse>() {
                        @Override
                        public void onSuccess(@NotNull QuotesResponse quotesResponse) {
                            Logger.log("TAG", quotesResponse.data.toString());
                            quotesResponseMutableLiveData.setValue(
                                    Response.success(quotesResponse)
                            );
                        }

                        @Override
                        public void onError(@NotNull Throwable exception) {
                            Logger.error(exception);
                        }
                    }
                )
        );
    }

    public void getUserInfo() {
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
                            Logger.error(e);
                       }
                   }
                )
        );
    }

    public void getAllPhotos() {
        compositeDisposable.add(getAllPhotosUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<List<Photo>>() {
                   @Override
                   public void onSuccess(@NotNull List<Photo> photos) {
                       photosResponseMutableLiveData.postValue(Response.success(photos));
                   }

                   @Override
                   public void onError(@NotNull Throwable e) {

                   }
               }
            )
        );
    }

    public void deletePhotoByUid(int uid) {
        compositeDisposable.add(deletePhotoUseCase.execute(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                        new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                deletePhotoMutableLiveData.setValue(Response.success(true));
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {
                                deletePhotoMutableLiveData.setValue(Response.success(false));
                            }
                        }
                )
        );
    }

    public void insertPhoto(Photo photo) {
        compositeDisposable.add(insertPhotoUseCase.execute(photo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableCompletableObserver() {
                   @Override
                   public void onComplete() {
                       insertPhotosResponseMutableLiveData.postValue(Response.success(true));
                       Logger.log("insertPhoto", "complete");

                   }

                   @Override
                   public void onError(@NotNull Throwable e) {
                       Logger.error(e);

                   }
               }
            )
        );
    }

//    public void getCat() {
//        compositeDisposable.add(getCatUseCase.execute()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(new DisposableSingleObserver<CatResponse>() {
//                   @Override
//                   public void onSuccess(@NotNull CatResponse catResponse) {
//                       catResponseMutableLiveData.postValue(
//                               Response.success(catResponse));
//                       Logger.log("cat image:",catResponse.getCatImage());
//                   }
//
//                   @Override
//                   public void onError(@NotNull Throwable e) {
//                        Logger.error(e);
//                   }
//               }
//            )
//        );
//    }

}
