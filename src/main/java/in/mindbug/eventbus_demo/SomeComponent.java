package in.mindbug.eventbus_demo;

import com.google.common.eventbus.EventBus;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class SomeComponent extends Composite {

	public SomeComponent() {
		// TODO Auto-generated constructor stub
		VerticalLayout root = new VerticalLayout();
		setCompositionRoot(root);

		Button button = new Button("Login", VaadinIcons.SIGN_IN);
		root.addComponent(button);

		button.addClickListener(e -> {
			EventBus eventBus = UI.getCurrent().getSession().getAttribute(EventBus.class);
			eventBus.post(new LoginSuccessEvent("user-xyz"));
		});
	}
}
