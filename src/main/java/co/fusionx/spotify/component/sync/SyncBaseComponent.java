package co.fusionx.spotify.component.sync;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.fusionx.spotify.component.BaseComponent;
import co.fusionx.spotify.json.JacksonConverter;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.util.Util;
import retrofit.RestAdapter;

public class SyncBaseComponent implements BaseComponent {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected static final String SPOTIFY_URL = "https://api.spotify.com/v1";

    protected final RestAdapter mRestAdapter;

    public SyncBaseComponent() {
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