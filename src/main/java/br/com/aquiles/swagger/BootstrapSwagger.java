package br.com.aquiles.swagger;

import br.com.aquiles.core.exception.PropertiesLoaderException;
import br.com.aquiles.core.util.PropertiesUtilities;
import io.swagger.annotations.Api;
import io.swagger.jaxrs.config.BeanConfig;
import org.reflections.Reflections;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Iterator;
import java.util.Properties;

public class BootstrapSwagger extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

        String version;
        Properties properties = null;
        try {
            properties = PropertiesUtilities.loadProperties("META-INF/aquiles-swagger.properties");
            version = properties.getProperty("version");
        } catch (PropertiesLoaderException e) {
            version = "unknown";
        }

        super.init(config);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Aquiles Swagger");
        beanConfig.setDescription("WebService Manager with Swagger");
        beanConfig.setVersion(version);
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath(config.getServletContext().getContextPath() + "/ws");
        addResourceAnnotatedWithApiSwagger(beanConfig);
        beanConfig.setScan(true);
    }

    private void addResourceAnnotatedWithApiSwagger(BeanConfig beanConfig) {
        //try with "br" package
        Iterator it = (new Reflections("br.com")).getTypesAnnotatedWith(Api.class).iterator();
        while (it.hasNext()) {
            Class clazz = (Class) it.next();
            String pack = clazz.getPackage().getName();
            beanConfig.setResourcePackage(pack);
            break;
        }
    }
}
