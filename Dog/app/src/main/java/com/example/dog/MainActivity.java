package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.dog.model.DogBreed;
import com.example.dog.modelview.DogApiService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private DogApiService dogApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogApiService = new DogApiService();
        dogApiService.getAllDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
//                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
//                    @Override
//                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
//                        for (DogBreed dog:dogBreeds) Log.d("DEBUG1", dog.name);
//                    }
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                    }
//                });
    }
}