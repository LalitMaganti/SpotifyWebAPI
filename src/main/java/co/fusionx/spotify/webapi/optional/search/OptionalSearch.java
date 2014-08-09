package co.fusionx.spotify.webapi.optional.search;

public class OptionalSearch {

    private static OptionalSearch sDefault;

    private final int mLimit;

    private final int mOffset;

    private OptionalSearch(int limit, int offset) {
        mLimit = limit;
        mOffset = offset;
    }

    public int getLimit() {
        return mLimit;
    }

    public int getOffset() {
        return mOffset;
    }

    public static OptionalSearch getDefault() {
        if (sDefault == null) {
            sDefault = new Builder().build();
        }
        return sDefault;
    }

    public static class Builder {

        // Default specified by Spotify
        private int mLimit = 20;

        // Default specified by Spotify
        private int mOffset = 0;

        public int getLimit() {
            return mLimit;
        }

        public int getOffset() {
            return mOffset;
        }

        public Builder setLimit(int limit) {
            mLimit = limit;
            return this;
        }

        public Builder setOffset(int offset) {
            mOffset = offset;
            return this;
        }

        public OptionalSearch build() {
            return new OptionalSearch(mLimit, mOffset);
        }
    }

    public enum SearchType {
        ALBUM("album"),
        ARTIST("artist"),
        TRACK("track");

        private final String mSearchType;

        private SearchType(String type) {
            mSearchType = type;
        }

        public String getSearchType() {
            return mSearchType;
        }
    }
}