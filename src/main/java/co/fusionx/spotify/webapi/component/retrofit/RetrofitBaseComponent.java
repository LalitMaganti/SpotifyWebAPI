package co.fusionx.spotify.webapi.component.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.fusionx.spotify.webapi.component.BaseComponent;
import co.fusionx.spotify.webapi.json.JacksonConverter;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.webapi.util.Util;
import retrofit.RestAdapter;

public class RetrofitBaseComponent implements BaseComponent {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected static final String SPOTIFY_URL = "https://api.spotify.com/v1";

    protected final RestAdapter mRestAdapter;

    public RetrofitBaseComponent() {
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(SPOTIFY_URL)
                .setConverter(new JacksonConverter(OBJECT_MAPPER))
                .build();
    }

    @Override
    public <T, U> U previousPage(final PagingObject<T> pagingObject, final Class<U> result) {
        if (Util.isEmpty(pagingObject.getPrevious())) {
            return null;
        }
        return Util.getJsonPojoFromUrl(pagingObject.getPrevious(), result);
    }

    @Override
    public <T, U> U nextPage(final PagingObject<T> pagingObject, final Class<U> result) {
        if (Util.isEmpty(pagingObject.getNext())) {
            return null;
        }
        return Util.getJsonPojoFromUrl(pagingObject.getNext(), result);
    }
}