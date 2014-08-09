package co.fusionx.spotify.webapi.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class JacksonConverter implements Converter {

    private ObjectMapper mObjectMapper;

    public JacksonConverter(final ObjectMapper objectMapper) {
        mObjectMapper = objectMapper;
    }

    @Override
    public Object fromBody(final TypedInput body, final Type type) throws ConversionException {
        final JavaType javaType = mObjectMapper.getTypeFactory().constructType(type);
        try {
            return mObjectMapper.readValue(body.in(), javaType);
        } catch (IOException e) {
            throw new ConversionException(e);
        }
    }

    @Override
    public TypedOutput toBody(final Object object) {
        try {
            String charset = "UTF-8";
            return new JsonTypedOutput(mObjectMapper.writeValueAsString(object).getBytes(charset),
                    charset);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static class JsonTypedOutput implements TypedOutput {

        private final byte[] jsonBytes;

        private final String mimeType;

        JsonTypedOutput(byte[] jsonBytes, String encode) {
            this.jsonBytes = jsonBytes;
            this.mimeType = "application/json; charset=" + encode;
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return mimeType;
        }

        @Override
        public long length() {
            return jsonBytes.length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }
    }
}