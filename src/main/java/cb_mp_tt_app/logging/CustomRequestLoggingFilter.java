package cb_mp_tt_app.logging;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Custom Logging Filter which logs HTTP requests and responses to rest endpoints
 */
public class CustomRequestLoggingFilter extends OncePerRequestFilter {

    private static final String SEPARATOR = "\n-------------------------------------------------------\n";
    private static final String REQUEST_MESSAGE_FORMAT = "request URI: [%s]\n" +
            "request method: [%s]\n" +
            "content type: [%s]\n" +
            "body: [%s]\n";
    private static final String COMPLETE_REQUEST_FORMAT = SEPARATOR + REQUEST_MESSAGE_FORMAT + SEPARATOR;
    private static final String RESPONSE_MESSAGE_FORMAT = "status: [%s]\n" +
            "content type: [%s]\n" +
            "body: [%s]\n";
    private static final String COMPLETE_RESPONSE_FORMAT = SEPARATOR + RESPONSE_MESSAGE_FORMAT + SEPARATOR;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        String requestString = String.format(COMPLETE_REQUEST_FORMAT,
                requestWrapper.getRequestURI(), requestWrapper.getMethod(),
                requestWrapper.getContentType(), new String(requestWrapper.getContentAsByteArray(), UTF_8));
        logger.debug(requestString);

        String responseString = String.format(COMPLETE_RESPONSE_FORMAT,
                responseWrapper.getStatus(), responseWrapper.getContentType(),
                new String(responseWrapper.getContentAsByteArray(), UTF_8));

        logger.debug(responseString);
        responseWrapper.copyBodyToResponse();
    }


}
