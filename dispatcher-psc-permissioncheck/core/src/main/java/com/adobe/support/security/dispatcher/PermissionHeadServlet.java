package com.adobe.support.security.dispatcher;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service
@Property(name="sling.servlet.paths", value= {"/bin/permissioncheck"})
public class PermissionHeadServlet extends SlingSafeMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(PermissionHeadServlet.class);
    
    public void doHead(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        String uri = request.getParameter("uri");
        Resource test = request.getResourceResolver().getResource(uri);
        if (test != null) {
            response.setStatus(SlingHttpServletResponse.SC_OK);
        } else {
            response.setStatus(SlingHttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
