package co.fusionx.spotify.webapi.util;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import co.fusionx.spotify.webapi.component.retrofit.RetrofitBaseComponent;

public class Util {

    public static boolean isEmpty(CharSequence previous) {
        return previous == null || previous.length() == 0;
    }

    public static <U> U getJsonPojoFromUrl(String url, Class<U> result) {
        try {
            return RetrofitBaseComponent.OBJECT_MAPPER.readValue(new URL(url), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String join(final Collection<String> list, final String conjoiner) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        final StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (final String item : list) {
            if (first) {
                first = false;
            } else {
                sb.append(conjoiner);
            }
            sb.append(item);
        }
        return sb.toString();
    }
}
