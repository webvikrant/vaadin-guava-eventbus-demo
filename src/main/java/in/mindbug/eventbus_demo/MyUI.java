package in.mindbug.eventbus_demo;

import javax.servlet.annotation.WebServlet;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();

		setContent(layout);

		// register your eventbus
		EventBus eventBus = new EventBus();
		eventBus.register(this);

		// store this eventbus in session, so that its accessible everywhere
		UI.getCurrent().getSession().setAttribute(EventBus.class, eventBus);

		// add some component, this could be anywhere deep down the UI component
		// hierarchy
		SomeComponent someComponent = new SomeComponent();
		layout.addComponent(someComponent);
	}

	@Subscribe
	public void onLoginSuccessful(LoginSuccessEvent event) {
		Notification notification = new Notification("Login successful for " + event.getUser(), Type.TRAY_NOTIFICATION);
		notification.setPosition(Position.MIDDLE_CENTER);
		notification.show(Page.getCurrent());
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}
