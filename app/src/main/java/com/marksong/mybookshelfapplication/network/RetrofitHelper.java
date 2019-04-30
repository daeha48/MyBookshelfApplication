package com.marksong.mybookshelfapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.marksong.bookshelfapp.utils.ConstantsKt.ibookBase_URL;

public class RetrofitHelper {

    private static Retrofit bookFit;
    private static final String bookUrl = ibookBase_URL;

    public static Retrofit getBookUrlInstance(){
        if (bookFit == null){
            bookFit = new Retrofit.Builder()
                    .baseUrl(bookUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return bookFit;
    }
}
