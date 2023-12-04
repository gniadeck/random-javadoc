package dev.gniadeck.randomjavadoc.http;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OkHttpBasedHttpClient implements HttpClient {

    private final OkHttpClient okHttpClient;

    @Override
    public String get(String url) {
        var request = new Request.Builder()
                .url(url)
                .build();
        try(var response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
