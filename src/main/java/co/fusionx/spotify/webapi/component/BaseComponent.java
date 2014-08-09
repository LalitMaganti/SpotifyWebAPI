package co.fusionx.spotify.webapi.component;

import co.fusionx.spotify.model.PagingObject;

public interface BaseComponent {

    public <T, U> U previousPage(PagingObject<T> pagingObject, Class<U> result);

    public <T, U> U nextPage(PagingObject<T> pagingObject, Class<U> result);
}
