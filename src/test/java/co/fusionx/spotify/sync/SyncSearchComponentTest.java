package co.fusionx.spotify.sync;

import org.assertj.core.api.Condition;
import org.junit.Test;

import co.fusionx.spotify.component.SearchComponent;
import co.fusionx.spotify.component.sync.SyncSearchComponent;
import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;

public class SyncSearchComponentTest {

    private final SearchComponent mClient;

    public SyncSearchComponentTest() {
        mClient = new SyncSearchComponent();
    }

    @Test
    public void searchTest() {
        final PagingObject<? extends Artist> artistPagingObject = mClient.searchArtist("Coldplay");
        assertThat(filter(artistPagingObject.getItems()).having(new Condition<Artist>() {
            @Override
            public boolean matches(Artist value) {
                return value.getId().equals("4gzpq5DPGxSnKTe4SA8HAU");
            }
        }).get()).isNotEmpty();
    }
}
