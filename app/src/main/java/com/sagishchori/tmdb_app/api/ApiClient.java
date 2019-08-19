package com.sagishchori.tmdb_app.api;

import com.sagishchori.tmdb_app.api.interfaces.NowPlaying;
import com.sagishchori.tmdb_app.api.responses.GetLatestMoviesResponse;
import com.sagishchori.tmdb_app.utils.StringUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    public static final String API_KEY = "api_key=f51c193130cfc1955e8f474058ee6923";

    private static ApiClient apiClient;
    private static Retrofit retrofit = null;
    private String url;

    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }

        return apiClient;
    }

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    /**
     * Use this method to get data about the latest movies.
     *
     * @param callback
     * @return
     */
    public ApiClient nowPlaying(Callback<GetLatestMoviesResponse> callback) {
        NowPlaying.NowPlayingInterface nowPlayingInterface = ApiClient.getApiClient().create(NowPlaying.NowPlayingInterface.class);
        Call<GetLatestMoviesResponse> call = nowPlayingInterface.getLatestMovies();
        call.enqueue(callback);

        return this;
    }

    /**
     * A method to get the complete url to download the requested image. Since all images url includes a size parameter use
     * {@link com.sagishchori.tmdb_app.utils.ImageUtils} for the requested size.
     *
     * @param imageSize     The size value from {@link com.sagishchori.tmdb_app.utils.ImageUtils} as {@link String}
     * @param imagePath     The unique path of the image
     *
     * @return              The url as {@link String} to download the image
     */
    public static String getImageUrl(String imageSize, String imagePath) {
        return BASE_IMAGE_URL + imageSize + imagePath;
    }
}
