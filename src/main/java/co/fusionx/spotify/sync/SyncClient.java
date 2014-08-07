package co.fusionx.spotify.sync;

import co.fusionx.spotify.component.AlbumComponent;
import co.fusionx.spotify.component.ArtistComponent;
import co.fusionx.spotify.component.BaseComponent;
import co.fusionx.spotify.component.SearchComponent;
import co.fusionx.spotify.component.TrackComponent;

public interface SyncClient extends AlbumComponent, ArtistComponent, TrackComponent,
        SearchComponent, BaseComponent {
}