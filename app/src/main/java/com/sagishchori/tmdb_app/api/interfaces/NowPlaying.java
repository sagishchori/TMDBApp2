package com.sagishchori.tmdb_app.api.interfaces;

import com.sagishchori.tmdb_app.api.responses.GetLatestMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import static com.sagishchori.tmdb_app.api.ApiClient.API_KEY;

public class NowPlaying {

    private static final String NOW_PLAYING = "now_playing/?";

    public interface NowPlayingInterface {

        @Headers({
                "date: Fri, 16 Aug 2019 20:49:10 GMT",
                "access-control-request-method: *",
                "x-ratelimit-remaining: 39",
                "prism-request-messages: [{\"messageType\":\"info\",\"text\":\"{\\n  \\\"context\\\": \\\"request.empty-content-type.body\\\",\\n  \\\"description\\\": \\\"Endpoint Request not defined for this content type of empty-content-type.\\\",\\n  \\\"type\\\": \\\"error\\\"\\n}\",\"createdAt\":\"2019-08-16T20:49:09.979037129Z\"}]",
                "server: openresty",
                "vary: Accept-Encoding",
                "access-control-allow-methods: OPTIONS, GET, POST, PUT, PATCH, DELETE, HEAD, LINK, UNLINK",
                "content-type: application/json;charset=utf-8",
                "access-control-allow-origin: https://developers.themoviedb.org",
                "access-control-expose-headers: Content-Type, Prism-Request-Messages, Prism-Request-Validation, X-Ratelimit-Remaining, Vary, Access-Control-Allow-Credentials, Server, X-Ratelimit-Limit, X-Ratelimit-Reset, Access-Control-Expose-Headers, Access-Control-Allow-Origin, Cache-Control, Access-Control-Request-Method, Date, Prism-Response-Validation, Access-Control-Allow-Methods",
                "cache-control: public, max-age=21600",
                "access-control-allow-credentials: true",
                "prism-request-validation: Passed",
                "x-ratelimit-reset: 1565988552",
                "x-ratelimit-limit: 40"
        })
        @GET(NOW_PLAYING + API_KEY)
        Call<GetLatestMoviesResponse> getLatestMovies();

    }
}
