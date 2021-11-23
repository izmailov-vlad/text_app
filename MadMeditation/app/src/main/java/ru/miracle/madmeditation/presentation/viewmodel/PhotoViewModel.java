package ru.miracle.madmeditation.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.models.comparator.FeelingsComparator;
import ru.miracle.madmeditation.data.repository.profile.ProfileRepositoryImpl;
import ru.miracle.madmeditation.data.storage.db.AppDatabase;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.domain.usecase.photos.DeletePhotoUseCase;
import ru.miracle.madmeditation.domain.usecase.photos.DeletePhotoUseCaseImpl;

public class PhotoViewModel extends AndroidViewModel {

    private final DeletePhotoUseCase deletePhotoUseCase;

    public MutableLiveData<Response<Boolean>> deletePhotoMutableLiveData;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PhotoViewModel(@NonNull @NotNull Application application) {
        super(application);

        deletePhotoUseCase = new DeletePhotoUseCaseImpl(new ProfileRepositoryImpl(
                AppDatabase.getAppDatabase(application).photoDao())
        );

        deletePhotoMutableLiveData = new MutableLiveData<>();
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
}
